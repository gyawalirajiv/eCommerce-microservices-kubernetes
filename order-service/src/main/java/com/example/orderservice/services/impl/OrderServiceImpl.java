package com.example.orderservice.services.impl;

import com.example.commonsmodule.DTOs.*;
import com.example.commonsmodule.security.CommonSecurityUtils;
import com.example.orderservice.clients.*;
import com.example.orderservice.entities.DTOs.OrderDTO;
import com.example.orderservice.entities.Orders;
import com.example.orderservice.entities.OrderItem;
import com.example.orderservice.entities.enums.PaymentType;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
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
    private final BankAPIClient bankAPIClient;
    private final CreditCardAPIClient creditCardAPIClient;
    private final PayPalAPIClient payPalAPIClient;
    private final TransactionAPIClient transactionAPIClient;

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

        order.setPaymentType(orderDTO.getPaymentType());
        order.setUserId(CommonSecurityUtils.getCurrentUserId().orElse(null));
        order.setTotalAmount(totalAmount);
        order.setPaymentType(orderDTO.getPaymentType());

        // Save order
        order = orderRepository.save(order);

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setTotalAmount(totalAmount);
        orderDTO.setOrderItemList(orderDTO.getOrderItemList());

        // Payment Services call
        makePayment(totalAmount, orderDTO);

        for (OrderItem orderItem : order.getOrderItemList()) {
            orderItem.setOrders(order);
        }

        // Subtract total stock - order quantity, after placing order success
        for (OrderItem orderItem : order.getOrderItemList()) {
            ProductDTO productDTO = catalogAPIClient.get(orderItem.getProductId());
            productDTO.setInStock(orderItem.getQuantity());
            catalogAPIClient.reduceStock(productDTO);
        }

        // Call Shipping Service after order success
        ShippingDTO shippingDTO = shippingAPIClient.shipping(orderDTO);
        orderDTO.setShippingDTO(shippingDTO);
        return modelMapper.map(orderDTO, OrderDTO.class);
    }

    private void makePayment(Double totalAmount, OrderDTO orderDTO) {
        TransactionDTO transactionDTO = new TransactionDTO();
        PaymentType paymentType = orderDTO.getPaymentType();
        transactionDTO.setTotal(totalAmount);
        transactionDTO.setOrderId(orderDTO.getOrderId());
        transactionDTO.setTransactionCode(Math.random() + System.currentTimeMillis());
        transactionDTO.setPaymentMethod(orderDTO.getPaymentType().toString());

        switch (paymentType) {
            case BANK -> {
                BankDTO bankDTO = orderDTO.getBankDTO();
                bankDTO.setBalance(totalAmount);
                if (Boolean.FALSE.equals(bankAPIClient.paymentVerification((orderDTO.getBankDTO())).getBody()))
                    throw new RuntimeException("Bank account not found !!");
            }
            case CC -> {
                CreditCardDTO creditCardDTO = orderDTO.getCreditCardDTO();
                creditCardDTO.setBalance(totalAmount);
                if (Boolean.FALSE.equals(creditCardAPIClient.paymentVerification((orderDTO.getCreditCardDTO())).getBody()))
                    throw new RuntimeException("Credit Card not found !!");
            }
            case PP -> {
                PaypalDTO paypalDTO = orderDTO.getPaypalDTO();
                paypalDTO.setBalance(totalAmount);
                if (Boolean.FALSE.equals(payPalAPIClient.paymentVerification(orderDTO.getPaypalDTO()).getBody()))
                    throw new RuntimeException("Paypal account not found !!");

            }
            default -> throw new RuntimeException("No payment type found !!");
        }

        // Create transaction
        transactionAPIClient.createTransaction(transactionDTO);
    }
}
