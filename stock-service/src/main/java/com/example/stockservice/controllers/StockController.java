package com.example.stockservice.controllers;

import com.example.stockservice.entities.DTOs.StockDTO;
import com.example.stockservice.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping("/{productId}")
    public Integer getStocksByProductId(@PathVariable Long productId){
        return stockService.getStocksByProductId(productId);
    }

    @PostMapping("/{productId}/add/{amount}")
    public StockDTO addStocksByProductId(@PathVariable Long productId, @PathVariable Integer amount){
        return stockService.addToStock(productId, amount);
    }

    @PostMapping("/{productId}/minus/{amount}")
    public StockDTO minusStocksByProductId(@PathVariable Long productId, @PathVariable Integer amount){
        return stockService.minusToStock(productId, amount);
    }
}
