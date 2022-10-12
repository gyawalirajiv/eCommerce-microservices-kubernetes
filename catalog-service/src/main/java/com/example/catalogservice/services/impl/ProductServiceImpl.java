package com.example.catalogservice.services.impl;

import com.example.catalogservice.clients.StockAPIClient;
import com.example.catalogservice.entities.DTOs.ProductDTO;
import com.example.catalogservice.entities.Product;
import com.example.catalogservice.repositories.ProductRepository;
import com.example.catalogservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final StockAPIClient stockAPIClient;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO payload) {
        Product product = modelMapper.map(payload, Product.class);
        product = productRepository.save(product);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setInStock(payload.getInStock());
        stockAPIClient.addStock(productDTO);
        return productDTO;
    }

    @Override
    public ProductDTO find(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setInStock(stockAPIClient.getStocksByProductId(productDTO.getId()));
        return productDTO;
    }

    @Override
    public ProductDTO reduceStock(ProductDTO payload) {
        stockAPIClient.reduceStock(payload);
        return payload;
    }
}
