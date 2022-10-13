package com.example.orderservice.entities;

import com.example.orderservice.entities.enums.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private Long accountId;
    @Enumerated
    private PaymentType paymentType;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
}
