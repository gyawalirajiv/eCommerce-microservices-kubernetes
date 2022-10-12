package com.example.orderservice.services;

import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.DTOs.OrderItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<OrderDTO> getAllOrder();

    OrderDTO makeOrder(OrderDTO orderDTO);

}
