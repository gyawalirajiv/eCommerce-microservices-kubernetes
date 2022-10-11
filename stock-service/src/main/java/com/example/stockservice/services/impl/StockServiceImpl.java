package com.example.stockservice.services.impl;

import com.example.stockservice.entities.DTOs.StockDTO;
import com.example.stockservice.entities.Stock;
import com.example.stockservice.services.StockService;
import com.example.stockservice.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    @Override
    public Integer getStocksByProductId(Long productId) {
        return stockRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Invalid Product ID!"))
                .getInStock();
    }

    @Override
    @Transactional
    public StockDTO addToStock(Long productId, Integer amount) {
        Optional<Stock> stockOpt = stockRepository.findByProductId(productId);
        Stock stock;
        if(stockOpt.isPresent()){
            stock = stockOpt.get();
            stock.setInStock(stock.getInStock() + amount);
        } else {
            stock = new Stock();
            stock.setProductId(productId);
            stock.setInStock(amount);
        }
        stock = stockRepository.save(stock);
        return modelMapper.map(stock, StockDTO.class);
    }

    @Override
    @Transactional
    public StockDTO minusToStock(Long productId, Integer amount) {
        Stock stock = stockRepository.findByProductId(productId).orElseThrow(() -> new RuntimeException("Invalid Product ID!"));
        if(stock.getInStock() < amount) throw new RuntimeException("Stock Items not Enough!");
        stock.setInStock(stock.getInStock() - amount);
        return modelMapper.map(stock, StockDTO.class);
    }
}
