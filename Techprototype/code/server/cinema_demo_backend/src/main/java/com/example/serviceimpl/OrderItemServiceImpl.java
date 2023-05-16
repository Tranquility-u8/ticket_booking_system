package com.example.serviceimpl;

import com.example.dao.OrderItemDao;
import com.example.entity.OrderItemEntity;
import com.example.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public OrderItemEntity save(OrderItemEntity orderItem) {
        return orderItemDao.save(orderItem);
    }

    @Override
    public void deleteById(int id) {
        orderItemDao.deleteById(id);
    }

    @Override
    public Optional<OrderItemEntity> findById(int id) {
        return orderItemDao.findById(id);
    }

    @Override
    public List<OrderItemEntity> findAll() {
        return orderItemDao.findAll();
    }
}