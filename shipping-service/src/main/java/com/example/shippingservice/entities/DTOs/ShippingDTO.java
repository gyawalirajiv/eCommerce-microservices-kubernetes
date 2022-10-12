package com.example.shippingservice.entities.DTOs;

import lombok.Data;

@Data
public class ShippingDTO {
    private Long shippingId;
    private String city;
    private String state;
    private String stAddress;
    private Integer zip;
}
