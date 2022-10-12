package com.example.orderservice.clients;

import com.example.orderservice.entities.DTOs.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "catalog-service", url = "localhost:8089")
public interface ShippingAPIClient {
    @PostMapping("/shipping/")
    String shipping(@RequestBody OrderDTO orderDTO);
}
