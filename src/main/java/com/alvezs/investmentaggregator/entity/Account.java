package com.alvezs.investmentaggregator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private UUID accountId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;
}
