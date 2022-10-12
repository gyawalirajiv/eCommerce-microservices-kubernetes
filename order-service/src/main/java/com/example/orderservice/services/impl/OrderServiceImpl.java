package com.example.orderservice.services.impl;

import com.example.commonsmodule.DTOs.ProductDTO;
import com.example.commonsmodule.DTOs.ShippingDTO;
import com.example.commonsmodule.security.CommonSecurityUtils;
import com.example.orderservice.clients.CatalogAPIClient;
import com.example.orderservice.clients.ShippingAPIClient;
import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.Orders;
import com.example.orderservice.entities.OrderItem;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    private final ModelMapper modelMapper;
    private final CatalogAPIClient catalogAPIClient;
    private final ShippingAPIClient shippingAPIClient;

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Orders> orders = orderRepository.findAllByUserId(CommonSecurityUtils.getCurrentUserId().get());
        List<OrderDTO> orderDTOS = orders.stream().map(o -> modelMapper.map(o, OrderDTO.class)).collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    @Transactional
    public OrderDTO makeOrder(OrderDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        double totalAmount = 0.0;

        for (OrderItem orderItem : order.getOrderItemList()) {
            ProductDTO productDTO = catalogAPIClient.get(orderItem.getProductId());
            if (productDTO.getInStock() < orderItem.getQuantity()) throw new RuntimeException("Product out of Stock!");
            totalAmount += orderItem.getQuantity() * productDTO.getPrice();
        }

        // TODO:  check if payment is successful or not if success then only make order otherwise not
        order.setUserId(CommonSecurityUtils.getCurrentUserId().get());
        order.setTotalAmount(totalAmount);
        order = orderRepository.save(order);
        for(OrderItem orderItem: order.getOrderItemList()){
            orderItem.setOrders(order);
        }

        // Subtract total stock - order quantity, after placing order
        for (OrderItem orderItem : order.getOrderItemList()) {
            ProductDTO productDTO = catalogAPIClient.get(orderItem.getProductId());
            productDTO.setInStock(orderItem.getQuantity());
            catalogAPIClient.reduceStock(productDTO);
        }

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setTotalAmount(totalAmount);
        orderDTO.setOrderItemList(orderDTO.getOrderItemList());

        // Call Shipping Service after order success
        ShippingDTO shippingDTO = shippingAPIClient.shipping(orderDTO);
        orderDTO.setShippingDTO(shippingDTO);
        return modelMapper.map(orderDTO, OrderDTO.class);
    }
}
