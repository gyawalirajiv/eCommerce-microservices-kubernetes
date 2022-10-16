package com.example.commonsmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
