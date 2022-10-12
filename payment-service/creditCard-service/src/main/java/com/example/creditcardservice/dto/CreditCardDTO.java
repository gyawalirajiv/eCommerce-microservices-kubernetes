package com.example.creditcardservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String ccv;
    private Double cardLimit;
    private LocalDate expiryDate;
    private Double balance;
}
