package com.example.stockservice.controllers;

import com.example.commonsmodule.DTOs.ProductDTO;
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

    @PostMapping
    StockDTO addStock(@RequestBody ProductDTO product){
        return stockService.addToStock(product);
    }

    @PutMapping
    public StockDTO reduceStock(@RequestBody ProductDTO productDTO){
        return stockService.minusToStock(productDTO);
    }
}
