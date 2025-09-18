package com.alvezs.investmentaggregator.dto;

public record CreateAccountDTO(
        String description,
        String street,
        Integer number
) {
}
