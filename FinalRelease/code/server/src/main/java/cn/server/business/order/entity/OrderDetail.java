package cn.server.business.order.entity;

import cn.server.common.entity.BaseEntity;
import lombok.Data;


@Data
public class OrderDetail extends BaseEntity {
    //电影名称
    private String movieName;
    //总价
    private Double price;
    //订单号
    private String ordernum;
    //电影数量
    private Integer num;

}
