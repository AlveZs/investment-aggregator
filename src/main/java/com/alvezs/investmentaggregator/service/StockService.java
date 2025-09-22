package com.alvezs.investmentaggregator.service;

import com.alvezs.investmentaggregator.dto.CreateStockDTO;
import com.alvezs.investmentaggregator.entity.Stock;
import com.alvezs.investmentaggregator.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(CreateStockDTO createStockDTO) {
        var stock = new Stock(
                createStockDTO.stockId(),
                createStockDTO.description()
        );

        return stockRepository.save(stock);
    }
}
