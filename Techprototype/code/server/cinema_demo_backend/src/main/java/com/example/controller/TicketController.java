package com.example.controller;

import com.example.entity.TicketEntity;
import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/create")
    public TicketEntity create(@ModelAttribute TicketEntity formData,
                               @RequestBody(required = false) TicketEntity jsonData) {
        TicketEntity ticket = (jsonData != null) ? jsonData : formData;
        return ticketService.save(ticket);
    }

    @RequestMapping("/delete")
    public void deleteById(@RequestParam int id) {
        ticketService.deleteById(id);
    }

    @RequestMapping("/getById")
    public Optional<TicketEntity> getById(@RequestParam int id) {
        return ticketService.findById(id);
    }

    @RequestMapping("/getAll")
    public List<TicketEntity> getAll() {
        return ticketService.findAll();
    }
}
