package com.example.catalogservice.services;

import com.example.catalogservice.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
}
