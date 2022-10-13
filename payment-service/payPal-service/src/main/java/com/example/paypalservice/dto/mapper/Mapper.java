package com.example.paypalservice.dto.mapper;

import com.example.paypalservice.dto.PaypalDTO;
import com.example.paypalservice.entity.Paypal;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PaypalDTO mapToDto(Paypal paypal){
        PaypalDTO paypalDto= PaypalDTO.builder().firstName(paypal.getFirstName())
                .lastName(paypal.getLastName())
                .balance(paypal.getBalance())
                .emailAddress(paypal.getEmailAddress())
                .secureKey(paypal.getSecureKey())
                .build();
        return paypalDto;
    }

   public Paypal mapToPaypal(PaypalDTO paypalDto){
        Paypal paypal=Paypal.builder()
                .firstName(paypalDto.getFirstName())
                .lastName(paypalDto.getLastName())
                .emailAddress(paypalDto.getEmailAddress())
                .secureKey(paypalDto.getSecureKey())
                .balance(paypalDto.getBalance())
                .build();
        return paypal;
   }

}
