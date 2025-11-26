package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewUserTicketRequest(
        @NotNull Integer userId,
        @NotBlank String userName,
        @Email String email) {
}
