package com.alvezs.investmentaggregator.repository;

import com.alvezs.investmentaggregator.entity.AccountStock;
import com.alvezs.investmentaggregator.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
