package com.example.controller;

import com.example.entity.CinemaEntity;
import com.example.entity.MovieEntity;
import com.example.entity.ScreeningEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screening")
@CrossOrigin(origins = "http://localhost:3000")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private EntityManager em;

    @RequestMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ScreeningEntity formData,
                                    @RequestBody(required = false) ScreeningEntity jsonData) {
        ScreeningEntity screening = (jsonData != null) ? jsonData : formData;
        int cinemaId = screening.getCinemaId();
        int movieId = screening.getMovieId();
        Optional<CinemaEntity> cinema = cinemaService.findById(cinemaId);
        Optional<MovieEntity> movie = movieService.findById(movieId);
        if (!cinema.isPresent() || !movie.isPresent()) {
            // 返回错误信息给用户
            return ResponseEntity.badRequest().body("Invalid cinemaId or movieId");
        } else {
            // 保存放映信息
            ScreeningEntity savedScreening = screeningService.save(screening);
            return ResponseEntity.ok(savedScreening);
        }
    }

    @RequestMapping("/delete")
    public void deleteById(@RequestParam int id) {
        screeningService.deleteById(id);
    }

    @RequestMapping("/getById")
    public ResponseEntity<ScreeningEntity> getById(@RequestParam int id) {
        Optional<ScreeningEntity> screening = screeningService.findById(id);
        if (screening.isPresent()) {
            return ResponseEntity.ok(screening.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<ScreeningEntity> search(@RequestParam(required = false) Integer cinemaId,
                                        @RequestParam(required = false) Integer movieId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ScreeningEntity> query = cb.createQuery(ScreeningEntity.class);
        Root<ScreeningEntity> root = query.from(ScreeningEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (cinemaId != null) {
            predicates.add(cb.equal(root.get("cinema").get("id"), cinemaId));
        }
        if (movieId != null) {
            predicates.add(cb.equal(root.get("movie").get("id"), movieId));
        }
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }
}
