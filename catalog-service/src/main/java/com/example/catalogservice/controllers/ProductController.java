package com.example.catalogservice.controllers;

import com.example.catalogservice.entities.DTOs.ProductDTO;
import com.example.catalogservice.entities.Product;
import com.example.catalogservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Long id){
        return productService.find(id);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO payload){
        return productService.save(payload);
    }

}