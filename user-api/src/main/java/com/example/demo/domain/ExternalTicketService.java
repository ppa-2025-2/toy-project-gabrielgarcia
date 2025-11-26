package com.example.demo.domain;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalTicketService implements ITicketService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalTicketService.class);

    private final RestTemplate http;
    private final String ticketServiceUrl;

    public ExternalTicketService(
            RestTemplate http,
            @Value("${ticket.service.url}") String ticketServiceUrl) {
        this.http = http;
        this.ticketServiceUrl = Objects.requireNonNull(ticketServiceUrl, "ticket.service.url");
    }

    @Override
    public void createTicketsForNewUser(Integer userId, String userName, String email) {
        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(userName, "userName");
        Objects.requireNonNull(email, "email");
        final var url = Objects.requireNonNull(ticketServiceUrl, "ticket.service.url");
        logger.info("Chamando serviço de tickets {}", url);
        http.postForEntity(
                url,
                Map.of(
                        "userId", userId,
                        "userName", userName,
                        "email", email),
                Void.class);
    }
}
