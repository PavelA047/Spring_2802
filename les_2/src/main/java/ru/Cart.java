package ru;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final ProductService productService;
    private final List<Product> cardProductList = new ArrayList<>();

    public Cart(ProductService productService) {
        this.productService = productService;
    }

    public void addProductToCard(int id) {
        cardProductList.add(productService.getProductByID(id));
    }

    public boolean remove(int id) {
        if (cardProductList.isEmpty()) return false;
        else return cardProductList.remove(productService.getProductByID(id));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Products in your cart:\n");
        for (Product p : cardProductList) {
            sb.append(p).append(";\n");
        }
        return sb.toString();
    }
}
