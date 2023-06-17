package cn.server.business.movie.service;

import cn.server.business.movie.entity.Movie;
import cn.server.business.movie.entity.MovieHouse;
import cn.server.common.service.IBaseService;

import java.util.List;

/**
 * 电影service层接口
 */
public interface IMovieService extends IBaseService<Movie> {
    /**
     * 添加电影
     * @param movie
     */
    void addMovie(Movie movie);

    /**
     * 更新电影图片
     * @param movie
     */
    void updateMoviePic(Movie movie);

    /**
     * 修改电影
     * @param movie
     */
    void editSaveMovie(Movie movie);

    /**
     * 删除电影
     * @param id
     */
    void deleteMovie(Long id);

    List<MovieHouse> queryAllMovieHouse();

    List<Movie> getMovieByHouseid(Long id);
}
