package cn.server.business.order.service;

import cn.server.business.order.entity.Order;
import cn.server.business.order.query.OrderQuery;
import cn.server.common.service.IBaseService;

import java.util.List;


public interface IOrderService extends IBaseService<Order> {

    void addOrder(Order order);

    List<Order> queryMyOrderByUsername(String username);

    List<Order> queryisCheckSeat(OrderQuery orderQuery);
}
