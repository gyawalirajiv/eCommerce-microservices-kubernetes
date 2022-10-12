package com.example.orderservice.clients;

import com.example.commonsmodule.DTOs.ShippingDTO;
import com.example.orderservice.entities.DTOs.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shipping-service", url = "localhost:8087")
public interface ShippingAPIClient {
    @PostMapping("/shippings/")
    ShippingDTO shipping(@RequestBody OrderDTO orderDTO);
}
