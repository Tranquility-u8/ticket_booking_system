package com.example.daoimpl;

import com.example.dao.OrderItemDao;
import com.example.entity.OrderItemEntity;
import com.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItemEntity save(OrderItemEntity orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteById(int id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public Optional<OrderItemEntity> findById(int id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItemEntity> findAll() {
        return orderItemRepository.findAll();
    }
}