package com.example.stockservice.services;

import com.example.stockservice.entities.DTOs.StockDTO;

public interface StockService {
    Integer getStocksByProductId(Long productId);

    StockDTO addToStock(Long productId, Integer amount);

    StockDTO minusToStock(Long productId, Integer amount);
}
