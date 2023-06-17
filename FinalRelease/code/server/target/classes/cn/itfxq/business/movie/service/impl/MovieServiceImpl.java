package cn.itfxq.business.movie.service.impl;

import cn.itfxq.business.movie.entity.Movie;
import cn.itfxq.business.movie.entity.MovieHouse;
import cn.itfxq.business.movie.mapper.MovieMapper;
import cn.itfxq.business.movie.service.IMovieService;
import cn.itfxq.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("movieService")
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements IMovieService {

    @Autowired
    private MovieMapper movieMapper;


    @Override
    public void addMovie(Movie movie) {
        //设置创建时间
        movie.setCreatetime(new Date());
        movieMapper.addMovie(movie);
    }

    /**
     * 更新电影图片
     * @param movie
     */
    @Override
    public void updateMoviePic(Movie movie) {
        movieMapper.updateMoviePic(movie);
    }

    /**
     * 修改保存电影
     * @param movie
     */
    @Override
    public void editSaveMovie(Movie movie) {
        movieMapper.editSaveMovie(movie);
    }

    /**
     * 删除电影
     * @param id
     */
    @Override
    public void deleteMovie(Long id) {
        movieMapper.deleteMovie(id);
    }

    @Override
    public List<MovieHouse> queryAllMovieHouse() {
        return movieMapper.queryAllMovieHouse();
    }

    @Override
    public List<Movie> getMovieByHouseid(Long id) {
        return movieMapper.getMovieByHouseid(id);
    }
}
