package com.example.orderservice.repositories;

import com.example.orderservice.entities.DTOs.OrderItemDTO;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
