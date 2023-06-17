package cn.server.business.order.service.impl;

import cn.server.business.order.entity.Order;
import cn.server.business.order.mapper.OrderMapper;
import cn.server.business.order.query.OrderQuery;
import cn.server.business.order.service.IOrderService;
import cn.server.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {
        order.setCreateTime(new Date());

        orderMapper.addOrder(order);
    }

    @Override
    public List<Order> queryMyOrderByUsername(String username) {
        return orderMapper.queryMyOrderByUsername(username);
    }

    @Override
    public List<Order> queryisCheckSeat(OrderQuery orderQuery) {
        return orderMapper.queryisCheckSeat(orderQuery);
    }
}
