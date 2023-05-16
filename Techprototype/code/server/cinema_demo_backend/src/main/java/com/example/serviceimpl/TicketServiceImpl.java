package com.example.serviceimpl;

import com.example.dao.TicketDao;
import com.example.entity.TicketEntity;
import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDao ticketDao;

    @Override
    public TicketEntity save(TicketEntity ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    public void deleteById(int id) {
        ticketDao.deleteById(id);
    }

    @Override
    public Optional<TicketEntity> findById(int id) {
        return ticketDao.findById(id);
    }

    @Override
    public List<TicketEntity> findAll() {
        return ticketDao.findAll();
    }
}
