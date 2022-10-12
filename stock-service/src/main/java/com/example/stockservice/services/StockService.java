package com.example.stockservice.services;

import com.example.commonsmodule.DTOs.ProductDTO;
import com.example.stockservice.entities.DTOs.StockDTO;

public interface StockService {
    Integer getStocksByProductId(Long productId);

    StockDTO addToStock(ProductDTO productDTO);

    StockDTO minusToStock(ProductDTO productDTO);
}
