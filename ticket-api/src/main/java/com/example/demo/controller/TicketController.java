package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.TicketService;
import com.example.demo.dto.NewUserTicketRequest;
import com.example.demo.repository.entity.Ticket;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(path = "/new-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicketsForNewUser(@Valid @RequestBody NewUserTicketRequest request) {
        ticketService.createNewUserTickets(request);
    }

    @GetMapping
    public List<Ticket> listTickets() {
        return ticketService.listTickets();
    }
}
