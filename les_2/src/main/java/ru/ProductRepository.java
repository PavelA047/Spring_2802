package ru;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {
    private final List<Product> productList = new ArrayList<>();
    private final AtomicLong identity = new AtomicLong(0);

    public void add(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductByID(int id) {
        return productList.get(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Products:\n");
        for (Product p : productList) {
            sb.append(p).append(";\n");
        }
        return sb.toString();
    }
}
