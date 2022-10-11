package com.example.catalogservice.clients;

import com.example.stockservice.entities.DTOs.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "stock-service", url = "localhost:8081")
public interface StockAPIClient {

    @GetMapping("/stocks/{productId}")
    Integer getStocksByProductId(@PathVariable Long productId);

    @PostMapping("/stocks/{productId}/add/{amount}")
    StockDTO addStocksByProductId(@PathVariable Long productId, @PathVariable Integer amount);

    @PostMapping("/stocks/{productId}/minus/{amount}")
    StockDTO minusStocksByProductId(@PathVariable Long productId, @PathVariable Integer amount);

}
