package com.example.orderservice.entities.DTOs;

import com.example.orderservice.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long orderItemId;
    private Long productId;
    private Double quantity;
    private OrderDTO order;
}
