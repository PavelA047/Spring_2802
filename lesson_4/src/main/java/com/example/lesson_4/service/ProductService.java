package com.example.lesson_4.service;

import com.example.lesson_4.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findProductsByFilter(String productFilter,
                                          String minProductFilter,
                                          String maxProductFilter,
                                          int page,
                                          int size,
                                          String sort);

    Optional<ProductDto> findProductById(long id);
    ProductDto saveProduct(ProductDto product);
    void deleteById(long id);
}
