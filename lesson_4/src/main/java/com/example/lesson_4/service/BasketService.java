package com.example.lesson_4.service;

import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.dto.UserDto;

public interface BasketService {
    ProductDto save(ProductDto product, UserDto user);
    void delete(ProductDto product, UserDto user);
}
