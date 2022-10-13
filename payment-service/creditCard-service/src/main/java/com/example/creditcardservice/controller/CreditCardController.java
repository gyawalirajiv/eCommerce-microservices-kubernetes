package com.example.creditcardservice.controller;

import com.example.creditcardservice.dto.CreditCardDTO;
import com.example.creditcardservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<?> saveCreditCard(@RequestBody CreditCardDTO creditCardDTO){
        ResponseEntity<?> response= new ResponseEntity<>(creditCardService.saveCreditCard(creditCardDTO), HttpStatus.OK);
        return  response;
    }

    @PostMapping("/payment-verification")
    public  ResponseEntity<?> checkCreditCard(@RequestBody CreditCardDTO creditCardDTO){
        ResponseEntity<?> response=new ResponseEntity<>(creditCardService.checkCreditCard(creditCardDTO),HttpStatus.OK);
        return  response;
    }


}
