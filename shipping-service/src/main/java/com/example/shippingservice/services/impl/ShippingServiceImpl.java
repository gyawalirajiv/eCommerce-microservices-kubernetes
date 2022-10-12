package com.example.shippingservice.services.impl;

import com.example.commonsmodule.DTOs.OrderDTO;
import com.example.commonsmodule.security.CommonSecurityUtils;
import com.example.shippingservice.entities.DTOs.ShippingDTO;
import com.example.shippingservice.entities.Shipping;
import com.example.shippingservice.repositories.ShippingRepository;
import com.example.shippingservice.services.ShippingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    ShippingRepository shippingRepository;
    private final ModelMapper modelMapper;

    @Override
    public ShippingDTO shipping(OrderDTO orderDTO) {
        Shipping shipping = modelMapper.map(orderDTO.getShippingDTO(), Shipping.class);
        shipping.setUserId(CommonSecurityUtils.getCurrentUserId().get());
        shipping.setStAddress(orderDTO.getShippingDTO().getStAddress());
        shipping.setCity(orderDTO.getShippingDTO().getCity());
        shipping.setState(orderDTO.getShippingDTO().getState());
        shipping.setZip(orderDTO.getShippingDTO().getZip());
        shipping.setOrderId(orderDTO.getOrderId());
        shippingRepository.save(shipping);
        return modelMapper.map(shipping, ShippingDTO.class);
    }
}
