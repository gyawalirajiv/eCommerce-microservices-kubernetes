package com.example.orderservice.clients;

import com.example.commonsmodule.DTOs.PaypalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paypal-service", url = "${app.paypal-service-url}")
public interface PayPalAPIClient {
    @PutMapping("/paypals/payment-verification")
    public ResponseEntity<Boolean> paymentVerification(@RequestBody PaypalDTO paypalDTO);

    @PostMapping("/paypals")
    public ResponseEntity<PaypalDTO> savePaypal(@RequestBody PaypalDTO paypalDTO);
}
