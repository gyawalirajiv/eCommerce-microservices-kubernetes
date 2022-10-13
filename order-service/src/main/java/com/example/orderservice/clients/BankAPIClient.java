package com.example.orderservice.clients;

import com.example.bankservice.dto.BankDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bank-service", url = "localhost:8084")
public interface BankAPIClient {
    @PostMapping("/banks")
    public ResponseEntity<BankDTO> saveBankAccount(@RequestBody BankDTO bankDTO);

    @PutMapping("/banks/payment-verification")
    public ResponseEntity<Boolean> paymentVerification(@RequestBody BankDTO bankDTO);
}
