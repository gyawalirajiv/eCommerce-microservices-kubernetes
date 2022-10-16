package com.example.commonsmodule.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private  Long orderId;
    private  Double transactionCode;
    private Double total;
    private String paymentMethod;
}
