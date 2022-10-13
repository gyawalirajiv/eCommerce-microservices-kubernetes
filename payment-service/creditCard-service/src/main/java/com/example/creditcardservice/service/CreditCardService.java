package com.example.creditcardservice.service;

import com.example.creditcardservice.dto.CreditCardDTO;
import org.springframework.stereotype.Service;

@Service
public interface CreditCardService {

    CreditCardDTO saveCreditCard(CreditCardDTO creditCardDTO);
    boolean checkCreditCard(CreditCardDTO creditCardDTO);
}
