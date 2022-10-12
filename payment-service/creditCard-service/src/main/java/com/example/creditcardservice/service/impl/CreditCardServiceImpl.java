package com.example.creditcardservice.service.impl;

import com.example.creditcardservice.dto.CreditCardDTO;
import com.example.creditcardservice.dto.mapper.Mapper;
import com.example.creditcardservice.entities.Creditcard;
import com.example.creditcardservice.repository.CreditcardRepository;
import com.example.creditcardservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditcardRepository creditcardRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public CreditCardDTO saveCreditCard(CreditCardDTO creditCardDTO) {
        Creditcard creditcard = mapper.mapToCreditcard(creditCardDTO);
        creditcardRepository.save(creditcard);
        creditCardDTO.setExpiryDate(creditcard.getExpiryDate());
        creditCardDTO.setCardLimit(creditcard.getCardLimit());
        return creditCardDTO;
    }

    @Override
    public boolean checkCreditCard(CreditCardDTO creditCardDTO) {
        Optional<Creditcard> creditcardOptional = creditcardRepository.findCreditcardByCardNumberAndExpiryDateAndCcv(creditCardDTO.getCardNumber(), creditCardDTO.getExpiryDate(), creditCardDTO.getCcv());
        if (!validateCreditcard(creditcardOptional, creditCardDTO)) return false;
        return updateChanges(creditcardOptional.get(), creditCardDTO);
    }

    private boolean updateChanges(Creditcard creditcard, CreditCardDTO creditCardDTO) {
        Double totalNewBalance = creditCardDTO.getBalance() + creditcard.getBalance();
        creditcard.setBalance(totalNewBalance);
        creditcardRepository.save(creditcard);
        return true;
    }

    private boolean validateCreditcard(Optional<Creditcard> creditcardOptional, CreditCardDTO creditCardDTO) {
        if (!creditcardOptional.isPresent()) {
            System.out.println("//////////////////////////// Inavalid card !!..............");
            return false;
        }
        Creditcard creditcard = creditcardOptional.get();
        if (creditcard.getExpiryDate().isBefore(LocalDate.now())) {
            System.out.println("========================= Card has expired plz try later.............");
            return false;
        }
        if (creditcard.getCardLimit() < creditCardDTO.getBalance() + creditcard.getBalance()) {
            System.out.println("=============== Limitation of card has been exceeded.....");
            return false;
        }
        return true;
    }

}
