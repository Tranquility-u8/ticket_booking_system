package com.example.service;

import com.example.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    TicketEntity save(TicketEntity ticket);

    void deleteById(int id);

    Optional<TicketEntity> findById(int id);

    List<TicketEntity> findAll();
}
