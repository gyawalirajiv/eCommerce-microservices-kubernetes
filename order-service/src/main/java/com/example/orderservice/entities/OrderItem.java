package com.example.orderservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class OrderItem {
    @Id
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    @ManyToOne
    private Order order;
}
