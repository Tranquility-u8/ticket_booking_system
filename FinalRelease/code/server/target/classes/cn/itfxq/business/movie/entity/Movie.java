package cn.itfxq.business.movie.entity;

import cn.itfxq.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;


@Data
public class Movie extends BaseEntity {

     //电影名称
     private String  moviename;
     //电影封面
     private String fmUrl;
     //主演
     private String zy_actor;
     //导演
     private String dy_actor;
     //电影信息
     private String info;
     //总时间
     private String totaltime;
     //电影院
    private Long   houseid;
     //电影名称
     private MovieHouse movieHouse;
     //是否下架
     private boolean isdown;
     //票价
     private String price;
     //排片时间  比如 1厅:10:20 1厅:11:30
     private String pptime;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

}
