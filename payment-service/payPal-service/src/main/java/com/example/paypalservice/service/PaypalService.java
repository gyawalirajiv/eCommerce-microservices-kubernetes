package com.example.paypalservice.service;

import com.example.paypalservice.dto.PaypalDTO;
import org.springframework.stereotype.Service;

@Service
public interface PaypalService {
    boolean checkPaypal(PaypalDTO paypalDto);
    PaypalDTO savePaypal(PaypalDTO paypalDto);

}
