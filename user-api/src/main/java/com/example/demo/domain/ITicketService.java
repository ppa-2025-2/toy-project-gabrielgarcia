package com.example.demo.domain;

public interface ITicketService {
    void createTicketsForNewUser(Integer userId, String userName, String email);
}
