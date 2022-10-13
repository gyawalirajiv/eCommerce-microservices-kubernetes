package com.example.orderservice.clients;

import com.example.paypalservice.dto.PaypalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paypal-service", url = "localhost:8086")
public interface PayPalAPIClient {
    @PutMapping("/paypals/payment-verification")
    public ResponseEntity<Boolean> paymentVerification(@RequestBody PaypalDTO paypalDTO);

    @PostMapping("/paypals")
    public ResponseEntity<PaypalDTO> savePaypal(@RequestBody PaypalDTO paypalDTO);
}
