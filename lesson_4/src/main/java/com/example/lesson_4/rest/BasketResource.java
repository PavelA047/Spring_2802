package com.example.lesson_4.rest;

import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/v1/basket")
@RestController
public class BasketResource {
    private BasketService basketService;

    @Autowired
    public BasketResource(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ProductDto putProductIntoBasket(@RequestBody ProductDto product) {
        return basketService.save(product, null);
    }

    @PostMapping
    public void deleteProductFromBasket(@RequestBody ProductDto product) {
        basketService.delete(product, null);
    }
}
