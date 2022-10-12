package com.example.shippingservice.services;


import com.example.commonsmodule.DTOs.OrderDTO;
import com.example.shippingservice.entities.DTOs.ShippingDTO;
import org.springframework.stereotype.Service;

@Service
public interface ShippingService {
    ShippingDTO shipping(OrderDTO orderDTO);
}
