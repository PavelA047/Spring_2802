package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    //    @Autowired нужен, если нет фабричного метода с аннотацией @Bean в Config-классе | в бине более 1 конструктора
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductService() {
    }

    public void addProduct(Product product) {
        productRepository.add(product);
    }

    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    public Product getProductByID(int id) {
        return productRepository.getProductByID(id);
    }

    @Override
    public String toString() {
        return productRepository.toString();
    }
}
