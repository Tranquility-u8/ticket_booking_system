package cn.server.business.movie.mapper;

import cn.server.business.movie.entity.Movie;
import cn.server.business.movie.entity.MovieHouse;
import cn.server.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MovieMapper extends BaseMapper<Movie> {

    /**
     * 新增电影
     * @param movie
     */
    @Insert("insert into t_movie(moviename,fmUrl,zy_actor,dy_actor,info,totaltime,houseid,isdown,price,pptime,createtime)" +
            " values(#{moviename},#{fmUrl},#{zy_actor},#{dy_actor},#{info},#{totaltime},#{houseid},#{isdown},#{price},#{pptime},#{createtime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addMovie(Movie movie);

    /**
     * 更新电影图片
     * @param movie
     */
    @Update("update t_movie set fmUrl=#{fmUrl} where id=#{id}")
    void updateMoviePic(Movie movie);

    /**
     * 修改电影
     * @param movie
     */
    @Update("update t_movie set moviename=#{moviename}," +
            "zy_actor=#{zy_actor},dy_actor=#{dy_actor},info=#{info}," +
            "totaltime=#{totaltime},houseid=#{houseid},isdown=#{isdown}," +
            "price=#{price},pptime=#{pptime}   where id=#{id}")
    void editSaveMovie(Movie movie);

    /**
     * 删除电影
     * @param id
     */
    @Delete("delete from t_movie where id=#{id}")
    void deleteMovie(Long id);

    @Select("select * from t_moviehouse")
    List<MovieHouse> queryAllMovieHouse();


    List<Movie> getMovieByHouseid(Long id);
}
