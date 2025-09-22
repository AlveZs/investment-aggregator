package com.alvezs.investmentaggregator.controller;

import com.alvezs.investmentaggregator.dto.CreateStockDTO;
import com.alvezs.investmentaggregator.entity.Stock;
import com.alvezs.investmentaggregator.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody CreateStockDTO createStockDTO) {
        var createdStock = stockService.createStock(createStockDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }
}
