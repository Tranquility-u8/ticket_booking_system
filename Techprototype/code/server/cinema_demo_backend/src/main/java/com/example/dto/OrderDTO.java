package com.example.dto;

import java.util.List;

public class OrderDTO {
    private int userId;
    private List<OrderItemDTO> orderItems;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    // getters and setters
}