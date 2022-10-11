package com.example.catalogservice.services;

import com.example.catalogservice.entities.DTOs.ProductDTO;
import com.example.catalogservice.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();

    ProductDTO save(ProductDTO payload);

    ProductDTO find(Long id);
}
