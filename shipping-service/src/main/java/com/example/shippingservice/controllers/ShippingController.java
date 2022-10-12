package com.example.shippingservice.controllers;

import com.example.commonsmodule.DTOs.OrderDTO;
import com.example.shippingservice.entities.DTOs.ShippingDTO;
import com.example.shippingservice.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    ShippingService shippingService;

    @PostMapping
    ResponseEntity<ShippingDTO> shipping(@RequestBody OrderDTO orderDTO) {
        ShippingDTO shippingDTOSuccess = shippingService.shipping(orderDTO);
        return new ResponseEntity<>(shippingDTOSuccess, HttpStatus.OK);
    }
}
