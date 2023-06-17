package cn.movie.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class OrderEntity extends BaseEntity{
    private String ordernum;
    private String isPay;
    private String createTime;
    private Double price;
    private String address;
    private String username;
    private String tel;

    private Long movieid;
    private String moviename;
    private String housename;
    private String beginTime;
    private Long nums;
    private String seatVal;

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Long getNums() {
        return nums;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    public String getSeatVal() {
        return seatVal;
    }

    public void setSeatVal(String seatVal) {
        this.seatVal = seatVal;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}