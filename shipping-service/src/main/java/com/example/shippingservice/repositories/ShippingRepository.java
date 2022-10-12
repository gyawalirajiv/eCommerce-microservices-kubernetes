package com.example.shippingservice.repositories;

import com.example.shippingservice.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
