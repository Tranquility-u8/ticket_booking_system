package com.example.dao;

import com.example.entity.OrderItemEntity;
import java.util.List;
import java.util.Optional;

public interface OrderItemDao {
    OrderItemEntity save(OrderItemEntity orderItem);

    void deleteById(int id);

    Optional<OrderItemEntity> findById(int id);

    List<OrderItemEntity> findAll();
}
