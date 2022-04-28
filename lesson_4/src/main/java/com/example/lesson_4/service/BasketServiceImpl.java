package com.example.lesson_4.service;

import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.dto.UserDto;
import com.example.lesson_4.persist.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public ProductDto save(ProductDto product, UserDto user) {
        return basketRepository.addProductToUsersBasket(product, user);
    }

    @Override
    public void delete(ProductDto product, UserDto user) {
        basketRepository.deleteProductFromUsersBasket(product, user);
    }
}
