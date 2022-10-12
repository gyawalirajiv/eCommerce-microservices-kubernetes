package com.example.creditcardservice.service;

import com.example.creditcardservice.dto.CreditCardDTO;

public interface CreditCardService {

    CreditCardDTO saveCreditCard(CreditCardDTO creditCardDTO);
    boolean checkCreditCard(CreditCardDTO creditCardDTO);
}
