package com.example.catalogservice.entities.DTOs;

import com.example.catalogservice.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String vendor;
    private Category category;
    private Double price;
    private Integer inStock;
}
