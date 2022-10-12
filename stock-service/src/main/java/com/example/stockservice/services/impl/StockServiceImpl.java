package com.example.stockservice.services.impl;

import com.example.commonsmodule.DTOs.ProductDTO;
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
    public StockDTO addToStock(ProductDTO productDTO) {
        Optional<Stock> stockOpt = stockRepository.findByProductId(productDTO.getId());
        Stock stock;
        if(stockOpt.isPresent()){
            stock = stockOpt.get();
            stock.setInStock(stock.getInStock() + productDTO.getInStock());
        } else {
            stock = new Stock();
            stock.setProductId(productDTO.getId());
            stock.setInStock(productDTO.getInStock());
        }
        stock = stockRepository.save(stock);
        return modelMapper.map(stock, StockDTO.class);
    }

    @Override
    @Transactional
    public StockDTO minusToStock(ProductDTO productDTO) {
        Stock stock = stockRepository.findByProductId(productDTO.getId()).orElseThrow(() -> new RuntimeException("Invalid Product ID!"));
        if(stock.getInStock() < productDTO.getInStock()) throw new RuntimeException("Stock Items not Enough!");
        stock.setInStock(stock.getInStock() - productDTO.getInStock());
        return modelMapper.map(stock, StockDTO.class);
    }
}
