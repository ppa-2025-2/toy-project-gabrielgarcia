package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
