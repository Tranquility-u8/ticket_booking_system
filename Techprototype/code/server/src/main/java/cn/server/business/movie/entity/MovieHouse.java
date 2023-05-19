package cn.server.business.movie.entity;

import lombok.Data;


@Data
public class MovieHouse {
    private Long id;
    private String name;
    private String address;
    private String price;

}
