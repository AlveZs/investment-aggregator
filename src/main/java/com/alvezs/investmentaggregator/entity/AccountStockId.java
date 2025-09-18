package com.alvezs.investmentaggregator.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Embeddable
public class AccountStockId {

    private UUID accountId;

    private String stockId;
}
