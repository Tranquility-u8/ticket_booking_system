package cn.movie.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.movie.entity.MovieEntity;
import cn.movie.entity.MovieHouseEntity;
import cn.movie.entity.OrderEntity;

public enum RemoteDatas {
    INSTANCE;

    /**
     * 得到电影数据
     * @param resultJson
     * @return
     */
    public List<MovieEntity> getMovieList(String resultJson) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<MovieEntity>>() {
        }.getType();
        // 得到电影数据
        List<MovieEntity> bookList = gson.fromJson(resultJson, listType);
        return bookList;
    }

    public List<MovieHouseEntity> getMovieHouseList(String resultJson) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<MovieHouseEntity>>() {
        }.getType();
        List<MovieHouseEntity> movieHouseEntityList = gson.fromJson(resultJson, listType);
        return movieHouseEntityList;
    }

    public List<OrderEntity> getOrderList(String resultJson) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<OrderEntity>>() {
        }.getType();
        List<OrderEntity> orderList = gson.fromJson(resultJson, listType);
        return orderList;
    }
}
