package com.example.orderservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Order {
    @Id
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;
}
