package com.example.orderservice.controllers;

import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.DTOs.OrderItemDTO;
import com.example.orderservice.entities.OrderItem;
import com.example.orderservice.services.OrderService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    ResponseEntity<OrderDTO> makeOrder(@RequestBody OrderDTO orderList) {
        OrderDTO orderDTOSuccess = orderService.makeOrder(orderList);
        return new ResponseEntity<>(orderDTOSuccess, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<OrderDTO>> getAllOrderItem() {
        Long userId = 1L;
        List<OrderDTO> orderDTOSuccess = orderService.getAllOrder(userId);
        return new ResponseEntity<>(orderDTOSuccess, HttpStatus.OK);
    }
}
