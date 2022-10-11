package com.example.orderservice.entities.DTOs;

import com.example.orderservice.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private List<OrderItemDTO> orderItemList;
}
