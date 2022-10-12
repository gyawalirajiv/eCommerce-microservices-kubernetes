package com.example.shippingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Shipping {
    @Id
    private Long shippingId;
    private String city;
    private String state;
    private String stAddress;
    private Integer zip;
}
