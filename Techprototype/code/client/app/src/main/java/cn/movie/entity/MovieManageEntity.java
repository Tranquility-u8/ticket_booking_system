package cn.movie.entity;

import java.io.Serializable;


public class MovieManageEntity implements Serializable {

    private String moviename;
    private String moviehousename;
    private Long movieid;
    private String price;

    private String pptime;

    private String pparea;

    private String ppprice;

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getPptime() {
        return pptime;
    }

    public void setPptime(String pptime) {
        this.pptime = pptime;
    }

    public String getPparea() {
        return pparea;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPparea(String pparea) {
        this.pparea = pparea;
    }

    public String getPpprice() {
        return ppprice;
    }

    public void setPpprice(String ppprice) {
        this.ppprice = ppprice;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMoviehousename() {
        return moviehousename;
    }

    public void setMoviehousename(String moviehousename) {
        this.moviehousename = moviehousename;
    }


}
