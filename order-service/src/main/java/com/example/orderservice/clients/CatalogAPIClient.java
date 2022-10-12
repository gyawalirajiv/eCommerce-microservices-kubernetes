package com.example.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", url = "localhost:8089")
public interface CatalogAPIClient {
    @GetMapping("/products/{productId}")
    Double getProductPriceByProductId(@PathVariable Long productId);
}
