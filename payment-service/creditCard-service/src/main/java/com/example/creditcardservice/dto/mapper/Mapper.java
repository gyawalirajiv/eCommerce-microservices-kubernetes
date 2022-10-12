package com.example.creditcardservice.dto.mapper;

import com.example.creditcardservice.dto.CreditCardDTO;
import com.example.creditcardservice.entities.Creditcard;
import org.springframework.stereotype.Component;

import java.time.LocalDate;



@Component
public class Mapper {
    public Creditcard mapToCreditcard(CreditCardDTO creditCardDTO){
        Creditcard creditcard=Creditcard.builder()
                .firstName(creditCardDTO.getFirstName())
                .lastName(creditCardDTO.getLastName())
                .cardNumber(creditCardDTO.getCardNumber())
                .ccv(creditCardDTO.getCcv())
                .build();
        creditcard.setExpiryDate(LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        creditcard.setCardLimit(2000.0);
        return creditcard;
    }

    public  CreditCardDTO mapToDto(Creditcard creditcard){
        CreditCardDTO creditCardDTO= CreditCardDTO.builder()
                .firstName(creditcard.getFirstName())
                .lastName(creditcard.getLastName())
                .cardNumber(creditcard.getCardNumber())
                .ccv(creditcard.getCcv())
                .expiryDate(creditcard.getExpiryDate())
                .build();
        return  creditCardDTO;
    }
}
