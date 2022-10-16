package com.example.orderservice.entities.DTOs;

import com.example.bankservice.dto.BankDTO;
import com.example.commonsmodule.DTOs.ShippingDTO;
import com.example.creditcardservice.dto.CreditCardDTO;
import com.example.orderservice.entities.enums.PaymentType;
import com.example.paypalservice.dto.PaypalDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private PaymentType paymentType;
    private List<OrderItemDTO> orderItemList;
    private ShippingDTO shippingDTO;
    private BankDTO bankDTO;
    private CreditCardDTO creditCardDTO;
    private PaypalDTO paypalDTO;
}
