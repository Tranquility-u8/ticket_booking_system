package com.example.dao;

import com.example.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    OrderEntity save(OrderEntity order);

    void deleteById(int id);

    Optional<OrderEntity> findById(int id);

    List<OrderEntity> findAll();
}
