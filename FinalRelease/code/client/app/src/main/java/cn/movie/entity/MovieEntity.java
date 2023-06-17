package cn.movie.entity;

import java.io.Serializable;
import java.util.Date;

public class MovieEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String  moviename;
    private String fmUrl;
    private String zy_actor;
    private String dy_actor;
    private String info;
    private String totaltime;
    private Long   houseid;
    private boolean isdown;
    private String price;
    private String pptime;
    private MovieHouseEntity movieHouse;
    private String createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }

    public String getZy_actor() {
        return zy_actor;
    }

    public void setZy_actor(String zy_actor) {
        this.zy_actor = zy_actor;
    }

    public String getDy_actor() {
        return dy_actor;
    }

    public void setDy_actor(String dy_actor) {
        this.dy_actor = dy_actor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public Long getHouseid() {
        return houseid;
    }

    public void setHouseid(Long houseid) {
        this.houseid = houseid;
    }

    public boolean isIsdown() {
        return isdown;
    }

    public void setIsdown(boolean isdown) {
        this.isdown = isdown;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPptime() {
        return pptime;
    }

    public void setPptime(String pptime) {
        this.pptime = pptime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public MovieHouseEntity getMovieHouse() {
        return movieHouse;
    }

    public void setMovieHouse(MovieHouseEntity movieHouse) {
        this.movieHouse = movieHouse;
    }
}