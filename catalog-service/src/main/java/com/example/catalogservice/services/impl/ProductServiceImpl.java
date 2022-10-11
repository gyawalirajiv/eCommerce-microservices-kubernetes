package com.example.catalogservice.services.impl;

import com.example.catalogservice.entities.Product;
import com.example.catalogservice.repositories.ProductRepository;
import com.example.catalogservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
