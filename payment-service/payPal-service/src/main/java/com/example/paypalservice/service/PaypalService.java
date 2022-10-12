package com.example.paypalservice.service;

import com.example.paypalservice.dto.PaypalDto;

public interface PaypalService {
    boolean checkPaypal(PaypalDto paypalDto);
    PaypalDto savePaypal(PaypalDto paypalDto);

}
