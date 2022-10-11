package com.example.bankservice.dto.mapper;

import com.example.bankservice.dto.BankDTO;
import com.example.bankservice.entities.Bank;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Bank mapToBank(BankDTO bankDTO) {
        return Bank.builder()
                .firstName(bankDTO.getFirstName())
                .lastName(bankDTO.getLastName())
                .routingNumber((bankDTO.getRoutingNumber()))
                .balance(bankDTO.getBalance())
                .email(bankDTO.getEmail())
                .accountType(bankDTO.getAccountType())
                .bankAccountNumber(bankDTO.getBankAccountNumber())
                .build();
    }

    public BankDTO mapToDto(Bank bank){

        return BankDTO
                .builder()
                .balance(bank.getBalance())
                .firstName(bank.getFirstName())
                .lastName(bank.getLastName())
                .routingNumber(bank.getRoutingNumber())
                .email(bank.getEmail())
                .accountType(bank.getAccountType())
                .bankAccountNumber(bank.getBankAccountNumber())
                .build();

    }


}
