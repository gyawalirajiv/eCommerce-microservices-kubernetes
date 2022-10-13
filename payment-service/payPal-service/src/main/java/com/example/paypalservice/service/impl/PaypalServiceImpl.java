package com.example.paypalservice.service.impl;

import com.example.paypalservice.dto.PaypalDTO;
import com.example.paypalservice.repository.PayPalRepo;
import com.example.paypalservice.service.PaypalService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private PayPalRepo payPalRepo;

//    @Autowired
//    private Mapper mapper;

    @Override
    public boolean checkPaypal(PaypalDTO paypalDto) {
        return false;
    }

    @Override
    public PaypalDTO savePaypal(PaypalDTO paypalDto) {
        return null;
    }
}
