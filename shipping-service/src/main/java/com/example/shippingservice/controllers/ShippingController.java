package com.example.shippingservice.controllers;

import com.example.shippingservice.entities.DTOs.ShippingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    ResponseEntity<ShippingDTO> shipping(ShippingDTO shippingDTO){

        return new ResponseEntity(shippingDTO, HttpStatus.OK);
    }
}
