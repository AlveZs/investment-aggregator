package com.alvezs.investmentaggregator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Stock {

    @Id
    private String stockId;

    private String description;
}
