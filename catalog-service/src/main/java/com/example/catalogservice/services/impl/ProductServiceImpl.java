package com.example.catalogservice.services.impl;

import com.example.catalogservice.entities.DTOs.ProductDTO;
import com.example.catalogservice.entities.Product;
import com.example.catalogservice.repositories.ProductRepository;
import com.example.catalogservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO save(ProductDTO payload) {
        Product product = modelMapper.map(payload, Product.class);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO find(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
        return modelMapper.map(product, ProductDTO.class);
    }
}
