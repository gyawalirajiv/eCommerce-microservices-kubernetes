package com.example.paypalservice.service.impl;

import com.example.paypalservice.dto.PaypalDto;
import com.example.paypalservice.repository.PayPalRepo;
import com.example.paypalservice.service.PaypalService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private PayPalRepo payPalRepo;

    @Autowired
    private Mapper mapper;

    @Override
    public boolean checkPaypal(PaypalDto paypalDto) {
        return false;
    }

    @Override
    public PaypalDto savePaypal(PaypalDto paypalDto) {
        return null;
    }
}
