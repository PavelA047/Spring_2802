package ru;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        productService.addProduct(new Product("Socks", 500L));
        productService.addProduct(new Product("Jeans", 7000L));
        productService.addProduct(new Product("Jacket", 5000L));
        productService.addProduct(new Product("T-short", 1000L));
        productService.addProduct(new Product("Trousers", 500L));
        productService.addProduct(new Product("Shorts", 2000L));
        productService.addProduct(new Product("Skirt", 5000L));
        productService.addProduct(new Product("Hat", 2000L));
        productService.addProduct(new Product("Sunglasses", 4000L));
        productService.addProduct(new Product("Bag", 6000L));

        System.out.println(productService);

        Cart cart = context.getBean("cart", Cart.class);
        cart.addProductToCard(2);
        cart.addProductToCard(4);
        cart.addProductToCard(6);

        System.out.println(cart);

        cart.remove(4);

        System.out.println(cart);

        Cart cart2 = context.getBean("cart", Cart.class);

        System.out.println(cart2);
    }
}
