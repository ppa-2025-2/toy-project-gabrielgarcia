package com.example.demo.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.NewUserTicketRequest;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.TicketType;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public void createNewUserTickets(NewUserTicketRequest request) {
        Ticket onboarding = buildTicket(
                request.userId(),
                "Onboarding do user",
                "Preparar jornada de onboarding para %s".formatted(request.userName()),
                TicketType.ONBOARDING);

        Ticket workstationAllocation = buildTicket(
                request.userId(),
                "Alocacao da estacao",
                "Alocar estacao de trabalho para %s".formatted(request.userName()),
                TicketType.WORKSTATION);

        ticketRepository.saveAll(List.of(onboarding, workstationAllocation));
    }

    public List<Ticket> listTickets() {
        return ticketRepository.findAll();
    }

    private Ticket buildTicket(Integer userId, String title, String description, TicketType type) {
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setType(type);
        ticket.setPriority("MEDIUM");
        return ticket;
    }
}
