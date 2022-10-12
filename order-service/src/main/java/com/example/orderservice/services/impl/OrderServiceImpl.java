package com.example.orderservice.services.impl;

import com.example.commonsmodule.security.CommonSecurityUtils;
import com.example.orderservice.clients.CatalogAPIClient;
import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.DTOs.OrderItemDTO;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderItem;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    private final ModelMapper modelMapper;
    private final CatalogAPIClient catalogAPIClient;

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAllByUserId(CommonSecurityUtils.getCurrentUserId().get());
        List<OrderDTO> orderDTOS = orders.stream().map(o -> modelMapper.map(o, OrderDTO.class)).collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    public OrderDTO makeOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        double totalAmount = 0.0;

        for (OrderItem orderItem : order.getOrderItemList()) {
            Double price = catalogAPIClient.getProductPriceByProductId(orderItem.getProductId());
            totalAmount += orderItem.getQuantity() * price;
        }

        // TODO:  check if payment is successful or not if success then only make order otherwise not

        order.setUserId(CommonSecurityUtils.getCurrentUserId().get());
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }
}
