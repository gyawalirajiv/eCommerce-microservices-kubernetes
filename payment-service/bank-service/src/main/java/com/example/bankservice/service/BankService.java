package com.example.bankservice.service;

import com.example.bankservice.dto.BankDTO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@Service
public interface BankService {

    BankDTO save(BankDTO bankDTO);

    Boolean checkBankAccount(BankDTO bankDTO);
}
