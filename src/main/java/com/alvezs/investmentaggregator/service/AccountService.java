package com.alvezs.investmentaggregator.service;

import com.alvezs.investmentaggregator.dto.AccountStockResponseDTO;
import com.alvezs.investmentaggregator.dto.AssociateAccountStockDTO;
import com.alvezs.investmentaggregator.entity.AccountStock;
import com.alvezs.investmentaggregator.entity.AccountStockId;
import com.alvezs.investmentaggregator.repository.AccountRepository;
import com.alvezs.investmentaggregator.repository.AccountStockRepository;
import com.alvezs.investmentaggregator.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AccountStockRepository accountStockRepository;

    public AccountStock associateStock(String accountId, AssociateAccountStockDTO associateAccountStockDTO) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(associateAccountStockDTO.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var accountStockId = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                accountStockId,
                associateAccountStockDTO.quantity(),
                account,
                stock
        );

        return accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDTO> listStocks(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(accountStock -> new AccountStockResponseDTO(
                        accountStock.getStock().getStockId(),
                        accountStock.getQuantity(),
                        0.0
                ))
                .toList();
    }
}
