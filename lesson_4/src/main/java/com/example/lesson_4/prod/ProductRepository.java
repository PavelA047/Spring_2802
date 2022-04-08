package com.example.lesson_4.prod;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.save(new Product("Product 1", 1000L));
        this.save(new Product("Product 2", 1500L));
        this.save(new Product("Product 3", 2000L));
        this.save(new Product("Product 4", 2500L));
        this.save(new Product("Product 5", 3000L));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product getProductByID(long id) {
        return productMap.get(id);
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;
    }

    public void delete(long id) {
        productMap.remove(id);
    }
}
