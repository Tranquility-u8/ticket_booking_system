package cn.server.business.movie.controller.front;

import cn.server.business.movie.entity.Movie;
import cn.server.business.movie.entity.MovieHouse;
import cn.server.business.movie.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/frontmovie")
public class MovieFrontController {

    @Autowired
    private IMovieService movieService;


    @Value("${server.moviePic.path}")
    private String moviePicRequestPath;

    @RequestMapping("/all")
    @ResponseBody
    public List<Movie> queryAll(){
        List<Movie> movies = movieService.queryAll();

        movies =  movies.stream().map(movie -> {
            movie.setFmUrl(moviePicRequestPath+"/"+movie.getFmUrl());
            return movie;
        }).collect(Collectors.toList());

        return movies;
    }
    //根据电影院id查询电影
    @RequestMapping("/getMovieByHouseid")
    @ResponseBody
    public List<Movie> getMovieByHouseid(MovieHouse movieHouse){
        List<Movie> movies = movieService.getMovieByHouseid(movieHouse.getId());

        movies =  movies.stream().map(movie -> {
            movie.setFmUrl(moviePicRequestPath+"/"+movie.getFmUrl());
            return movie;
        }).collect(Collectors.toList());

        return movies;
    }

    @RequestMapping("/allmoviehouse")
    @ResponseBody
    public List<MovieHouse> queryAllMovieHouse(){
        List<MovieHouse> movieHouses = movieService.queryAllMovieHouse();


        return movieHouses;
    }
}
