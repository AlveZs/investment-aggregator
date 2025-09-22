package com.alvezs.investmentaggregator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    private Integer quantity;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    @JsonBackReference
    private Stock stock;
}
