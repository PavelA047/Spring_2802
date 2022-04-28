package com.example.lesson_4.persist;

import com.example.lesson_4.dto.BasketDto;
import com.example.lesson_4.dto.LineItem;
import com.example.lesson_4.dto.ProductDto;
import com.example.lesson_4.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BasketRepository {
    private BasketDto basket;

    @Autowired
    public BasketRepository(BasketDto basket) {
        this.basket = basket;
    }

    public ProductDto addProductToUsersBasket(ProductDto p, UserDto u) {
        LineItem tempLineItem = new LineItem(p, u);
        if (basket.getList().contains(tempLineItem)) {
            tempLineItem.setCountOfProducts(tempLineItem.getCountOfProducts() + 1);
        } else {
            basket.getList().add(tempLineItem);
        }
        return p;
    }

    public void deleteProductFromUsersBasket(ProductDto p, UserDto u) {
        LineItem tempLineItem = new LineItem(p, u);
        basket.getList().removeIf(lineItem -> lineItem.equals(tempLineItem));
    }
}
