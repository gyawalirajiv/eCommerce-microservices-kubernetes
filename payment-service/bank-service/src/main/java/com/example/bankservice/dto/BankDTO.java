package com.example.bankservice.dto;

import com.example.bankservice.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDTO {
        private  String firstName;
        private String lastName;
        private String email;
        private AccountType accountType;
        private  String routingNumber;
        private String bankAccountNumber;
        private Double balance;

}
