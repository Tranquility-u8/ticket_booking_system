package com.example.serviceimpl;

import com.example.dao.OrderDao;
import com.example.entity.OrderEntity;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderDao.save(order);
    }

    @Override
    public void deleteById(int id) {
        orderDao.deleteById(id);
    }

    @Override
    public Optional<OrderEntity> findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderDao.findAll();
    }
}