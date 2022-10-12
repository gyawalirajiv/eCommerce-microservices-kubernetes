package com.example.transactionservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private  Long orderId;
    private  Double transactionCode;
    private Double total;
    private String paymentMethod;
}