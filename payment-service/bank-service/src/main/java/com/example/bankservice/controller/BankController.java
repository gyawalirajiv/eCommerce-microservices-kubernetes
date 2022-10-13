package com.example.bankservice.controller;

import com.example.bankservice.dto.BankDTO;
import com.example.bankservice.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public ResponseEntity<?> saveBankAccount(@RequestBody BankDTO bankDTO) {
        ResponseEntity<BankDTO> response = new ResponseEntity<>(bankService.save(bankDTO), HttpStatus.OK);
        return response;
    }

    @PutMapping("/payment-verification")
    public ResponseEntity<?> checkBankAccount(@RequestBody BankDTO bankDTO) {
        ResponseEntity<?> response = new ResponseEntity<>(bankService.checkBankAccount(bankDTO), HttpStatus.OK);
        return response;
    }


}
