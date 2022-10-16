package com.example.orderservice.controllers;

import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.DTOs.OrderItemDTO;
import com.example.orderservice.entities.OrderItem;
import com.example.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody OrderDTO orderList) {
        OrderDTO orderDTOSuccess = orderService.makeOrder(orderList);
        return new ResponseEntity<>(orderDTOSuccess, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrderItem() {
        List<OrderDTO> orderDTOSuccess = orderService.getAllOrder();
        return new ResponseEntity<>(orderDTOSuccess, HttpStatus.OK);
    }
}
