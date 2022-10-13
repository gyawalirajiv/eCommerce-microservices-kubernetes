package com.example.paypalservice.service.impl;

import com.example.paypalservice.dto.PaypalDTO;
import com.example.paypalservice.dto.mapper.Mapper;
import com.example.paypalservice.entity.Paypal;
import com.example.paypalservice.repository.PayPalRepo;
import com.example.paypalservice.service.PaypalService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private PayPalRepo payPalRepo;

    @Autowired
    private Mapper mapper;

    @Override
    public boolean checkPaypal(PaypalDTO paypalDto) {
        Optional<Paypal> paypalOptional = payPalRepo.findByEmailAddressAndSecureKey(paypalDto.getEmailAddress(), paypalDto.getSecureKey());
        if (!validatePaypal(paypalOptional, paypalDto)) return false;
        return updateChange(paypalOptional.get(), paypalDto);
    }

    private boolean updateChange(Paypal paypal, PaypalDTO paypalDto) {
        Double balance = paypal.getBalance() - paypalDto.getBalance();
        payPalRepo.save(paypal);
        return true;
    }

    private boolean validatePaypal(Optional<Paypal> paypalOptional, PaypalDTO paypalDto) {
        if (paypalOptional.isEmpty()) {
            System.out.println("======Invalid Account!!==================");
            return false;
        }
        if (paypalOptional.get().getBalance() - paypalDto.getBalance() < 0) {
            System.out.println("===========>Insufficient balance  to purchase item<============");
            return false;
        }
        return true;
    }

    @Override
    public PaypalDTO savePaypal(PaypalDTO paypalDto) {
        Paypal paypal = mapper.mapToPaypal(paypalDto);
        payPalRepo.save(paypal);
        return paypalDto;
    }
}
