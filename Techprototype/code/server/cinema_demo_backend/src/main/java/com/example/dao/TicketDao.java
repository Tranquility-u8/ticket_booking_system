package com.example.dao;

import com.example.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface TicketDao {
    TicketEntity save(TicketEntity ticket);

    void deleteById(int id);

    Optional<TicketEntity> findById(int id);

    List<TicketEntity> findAll();
}
