package com.example.orderservice.entities.DTOs;

import com.example.commonsmodule.DTOs.BankDTO;
import com.example.commonsmodule.DTOs.CreditCardDTO;
import com.example.commonsmodule.DTOs.PaypalDTO;
import com.example.commonsmodule.DTOs.ShippingDTO;
import com.example.orderservice.entities.enums.PaymentType;
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
