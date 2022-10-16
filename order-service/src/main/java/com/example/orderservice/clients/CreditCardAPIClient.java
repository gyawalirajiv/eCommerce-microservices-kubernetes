package com.example.orderservice.clients;

import com.example.creditcardservice.dto.CreditCardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "creditCard-service", url = "localhost:8085")
public interface CreditCardAPIClient {
    @PostMapping("/creditcards/payment-verification")
    public ResponseEntity<Boolean> paymentVerification(@RequestBody CreditCardDTO creditCardDTO);
}
