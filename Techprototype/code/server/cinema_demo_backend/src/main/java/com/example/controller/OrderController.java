package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.dto.OrderItemDTO;
import com.example.entity.OrderEntity;
import com.example.entity.OrderItemEntity;
import com.example.service.OrderItemService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping("/create")
    public OrderEntity create(@ModelAttribute OrderDTO formData,
                            @RequestBody(required = false) OrderDTO jsonData) {
        OrderDTO orderDTO = (jsonData != null) ? jsonData : formData;

        // 计算总价
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            total = total.add(orderItemDTO.getRealPrice().multiply(new BigDecimal(orderItemDTO.getTicketCount())));
        }

        // 生成 Order 并保存
        OrderEntity order = new OrderEntity();
        order.setUserId(orderDTO.getUserId());
        order.setTotal(total);
        order = orderService.save(order);

        // 初始化各 OrderItem 的 orderId 字段并保存
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setTicketId(orderItemDTO.getTicketId());
            orderItem.setTicketCount(orderItemDTO.getTicketCount());
            orderItem.setRealPrice(orderItemDTO.getRealPrice());
            orderItemService.save(orderItem);
        }

        return order;
    }

    @RequestMapping("/deleteById")
    public void deleteById(@RequestParam int id) {
        orderService.deleteById(id);
    }

    @RequestMapping("/getById")
    public Optional<OrderEntity> findById(@PathVariable int id) {
        return orderService.findById(id);
    }

    @RequestMapping("/getAll")
    public List<OrderEntity> findAll() {
        return orderService.findAll();
    }
}
