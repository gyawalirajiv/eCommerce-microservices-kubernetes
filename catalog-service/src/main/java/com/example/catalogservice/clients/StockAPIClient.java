package com.example.catalogservice.clients;

import com.example.catalogservice.entities.DTOs.ProductDTO;
import com.example.commonsmodule.DTOs.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stock-service", url = "localhost:8082")
public interface StockAPIClient {

    @GetMapping("/stocks/{productId}")
    Integer getStocksByProductId(@PathVariable Long productId);

    @PostMapping("/stocks")
    StockDTO addStock(@RequestBody ProductDTO product);

    @PutMapping("/stocks")
    StockDTO reduceStock(@RequestBody ProductDTO productDTO);

}
