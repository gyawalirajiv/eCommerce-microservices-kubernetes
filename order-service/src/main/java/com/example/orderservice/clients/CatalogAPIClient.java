package com.example.orderservice.clients;

import com.example.commonsmodule.DTOs.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "catalog-service", url = "${app.catalog-service-url}")
public interface CatalogAPIClient {
    @GetMapping("/products/{id}")
    ProductDTO get(@PathVariable Long id);

    @PutMapping("/products")
    ProductDTO reduceStock(@RequestBody ProductDTO payload);
}
