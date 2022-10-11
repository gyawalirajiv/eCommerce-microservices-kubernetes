package com.example.bankservice.repository;

import com.example.bankservice.constant.AccountType;
import com.example.bankservice.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

 Optional<Bank> findBankByAccountTypeAndRoutingNumberAndBankAccountNumber(AccountType accountType,String routingNumber, String bankAccountNumber
 );
}
