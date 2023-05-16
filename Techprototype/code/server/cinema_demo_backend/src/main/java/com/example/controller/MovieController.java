package com.example.controller;

import com.example.entity.CinemaEntity;
import com.example.entity.MovieEntity;
import com.example.entity.ScreeningEntity;
import com.example.request.RegisterRequest;
import com.example.service.CinemaService;
import com.example.service.MovieService;
import com.example.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private EntityManager em;


    @RequestMapping("/create")
    public ResponseEntity<MovieEntity> createMovie(@ModelAttribute MovieEntity formData,
                                                   @RequestBody(required = false) MovieEntity jsonData) {

        MovieEntity movie = (jsonData != null) ? jsonData : formData;
        MovieEntity savedMovie = movieService.save(movie);
        Optional<MovieEntity> updatedMovie = movieService.findById(savedMovie.getId());
        if (updatedMovie.isPresent()) {
            return ResponseEntity.ok(updatedMovie.get());
        } else {
            return ResponseEntity.notFound().build();
        }// 直接返回则不包含MySQL给定的createDate默认值，需要再find一遍
    }

    @RequestMapping("/delete")
    public void deleteMovie(@RequestParam int id) {
        movieService.deleteById(id);
    }

    @RequestMapping("/getById")
    public ResponseEntity<MovieEntity> getMovieById(@RequestParam int id) {
        Optional<MovieEntity> movie = movieService.findById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // 以覆盖方法更新字段，若有些字段没有指定，则会覆盖成null
    @RequestMapping("/setById")
    public ResponseEntity<MovieEntity> setMovie(@RequestBody MovieEntity newMovie) {
        Optional<MovieEntity> movie = movieService.findById(newMovie.getId());
        if (movie.isPresent()) {
            MovieEntity updatedMovie = movie.get();
            movieService.save(updatedMovie);
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 以更新字段的方法设置，检测到null的字段不设置
    @RequestMapping("/updateById")
    public ResponseEntity<MovieEntity> updateMovie(@RequestBody Map<String, Object> newMovie) {
        int id = (int) newMovie.get("id");
        Optional<MovieEntity> movie = movieService.findById(id);
        if (movie.isPresent()) {
            MovieEntity updatedMovie = movie.get();
            // 检查请求体中是否包含某个字段的值
            if (newMovie.containsKey("title")) {
                String title = (String) newMovie.get("title");
                if (title != null) {
                    updatedMovie.setTitle(title);
                }
            }
            if (newMovie.containsKey("releaseDate")) {
                updatedMovie.setReleaseDate((String) newMovie.get("releaseDate"));
            }
            if (newMovie.containsKey("genre")) {
                updatedMovie.setGenre((String) newMovie.get("genre"));
            }
            if (newMovie.containsKey("director")) {
                updatedMovie.setDirector((String) newMovie.get("director"));
            }
            if (newMovie.containsKey("cast")) {
                updatedMovie.setCast((String) newMovie.get("cast"));
            }
            if (newMovie.containsKey("description")) {
                updatedMovie.setDescription((String) newMovie.get("description"));
            }
            if (newMovie.containsKey("posterUrl")) {
                updatedMovie.setPosterUrl((String) newMovie.get("posterUrl"));
            }
            if (newMovie.containsKey("movieUrl")) {
                updatedMovie.setMovieUrl((String) newMovie.get("movieUrl"));
            }
            // 更新其他字段
            // ...
            movieService.save(updatedMovie);
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping("/getAll")
    public List<MovieEntity> getAllMovies() {
        return movieService.findAll();
    }

//    @RequestMapping("/searchMovies")
//    public List<MovieEntity> searchMovies(// 根据电影名，类别，导演来找电影；这三个都可能有重复值，故返回一个表
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String genre,
//            @RequestParam(required = false) String director
//    ) {
//        if (title != null) {
//            return movieService.findByTitle(title);
//        } else if (genre != null) {
//            return movieService.findByGenre(genre);
//        } else if (director != null) {
//            return movieService.findByDirector(director);
//        } else {
//            return Collections.emptyList();
//        }
//    }
    // 多个可能条件都参与进来的查询
    @RequestMapping("/search")
    public List<MovieEntity> searchMovies(@RequestParam(required = false) String title,
                                          @RequestParam(required = false) String genre,
                                          @RequestParam(required = false) String director) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MovieEntity> query = cb.createQuery(MovieEntity.class);
        Root<MovieEntity> root = query.from(MovieEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (title != null) {
            predicates.add(cb.equal(root.get("title"), title));
        }
        if (genre != null) {
            predicates.add(cb.equal(root.get("genre"), genre));
        }
        if (director != null) {
            predicates.add(cb.equal(root.get("director"), director));
        }
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }
}