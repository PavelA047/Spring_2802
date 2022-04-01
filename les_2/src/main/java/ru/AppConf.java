package ru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru")
public class AppConf {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

//    @Bean
//    public ProductService productService(ProductRepository productRepository) {
//        return new ProductService(productRepository);
//    }

    @Bean
    @Scope("prototype")
    public Cart cart(ProductService productService) {
        return new Cart(productService);
    }
}