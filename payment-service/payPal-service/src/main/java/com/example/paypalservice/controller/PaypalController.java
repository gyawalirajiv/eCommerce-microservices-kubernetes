package com.example.paypalservice.controller;

import com.example.paypalservice.dto.PaypalDTO;
import com.example.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypals")
public class PaypalController {
    @Autowired
    private PaypalService paypalService;

    @PostMapping
    public ResponseEntity<?> savePaypal(@RequestBody PaypalDTO paypalDto){
        return new ResponseEntity<>(paypalService.checkPaypal(paypalDto), HttpStatus.OK);
    }

    @PutMapping("payment-verification")
    public ResponseEntity<?> checkPaypal(@RequestBody PaypalDTO paypalDto){
        return new ResponseEntity<>(paypalService.checkPaypal(paypalDto),HttpStatus.OK);
    }

}
