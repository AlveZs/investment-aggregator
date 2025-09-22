package com.alvezs.investmentaggregator.controller;

import com.alvezs.investmentaggregator.dto.AccountStockResponseDTO;
import com.alvezs.investmentaggregator.dto.AssociateAccountStockDTO;
import com.alvezs.investmentaggregator.entity.AccountStock;
import com.alvezs.investmentaggregator.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<AccountStock> associateStock(
            @PathVariable("accountId") String accountId,
            @RequestBody AssociateAccountStockDTO associateAccountStockDTO
    ) {
        var accountStock = accountService.associateStock(accountId, associateAccountStockDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountStock);
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> associateStock(
            @PathVariable("accountId") String accountId
    ) {
        var stocks = accountService.listStocks(accountId);

        return ResponseEntity.ok(stocks);
    }
}
