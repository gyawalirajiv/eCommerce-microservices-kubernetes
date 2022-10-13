package com.example.bankservice.service.impl;


import com.example.bankservice.dto.BankDTO;
import com.example.bankservice.dto.mapper.Mapper;
import com.example.bankservice.entities.Bank;
import com.example.bankservice.repository.BankRepository;
import com.example.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private BankRepository bankRepository;

    @Override
    public BankDTO save(BankDTO bankDTO) {
        return mapper.mapToDto(bankRepository.save(mapper.mapToBank(bankDTO)));
    }

    @Override
    public Boolean checkBankAccount(BankDTO bankDTO) {
        Optional<Bank> bankOptional= bankRepository.findBankByAccountTypeAndRoutingNumberAndBankAccountNumber(bankDTO.getAccountType(),bankDTO.getRoutingNumber(),bankDTO.getBankAccountNumber());
    if(!validateBankAccount(bankOptional,bankDTO)) return false;
    return updateChange(bankOptional.get(),bankDTO);
    }

    private Boolean updateChange(Bank bank, BankDTO bankDTO) {
        Double availableBalance=bank.getBalance() - bankDTO.getBalance();
        bank.setBalance(availableBalance);
        bankRepository.save(bank);
    return true;
    }

    private boolean validateBankAccount(Optional<Bank> bankOptional, BankDTO bankDTO) {
       if(!bankOptional.isPresent()){
           System.out.println("Invalid Bank Account !!");
           return false;
       }
       Bank bank=bankOptional.get();
       Double availableBalance=bank.getBalance() - bankDTO.getBalance();
       if(availableBalance<0){
           System.out.println("Insufficient balance in Account to purchase item.");
           return false;
       }
      return true;
    }
}
