package com.example.daoimpl;

import com.example.dao.TicketDao;
import com.example.entity.TicketEntity;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public TicketEntity save(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Optional<TicketEntity> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<TicketEntity> findAll() {
        return ticketRepository.findAll();
    }
}
