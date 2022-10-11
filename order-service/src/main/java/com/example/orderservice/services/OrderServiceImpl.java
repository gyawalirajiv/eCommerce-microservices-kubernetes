package com.example.orderservice.services;

import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.DTOs.OrderItemDTO;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderItem;
import com.example.orderservice.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrder(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderDTO> orderDTOS = orders.stream().map(o -> modelMapper.map(o, OrderDTO.class)).collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    public OrderDTO makeOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Double price = 0.0;
        Double totalAmount = 0.0;
        Long userId = 1L;

        for (OrderItem orderItem : order.getOrderItemList()) {

            // TODO: check product exists or not using orderItemDTO.getProductId() calling product service
            //  price will be returned by product service
            totalAmount += orderItem.getQuantity() * price;
            // TODO:  check if payment is successful or not if success then only make order otherwise not
        }

        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }
}
