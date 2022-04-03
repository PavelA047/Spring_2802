package ru.geekbrains.prod;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final List<Product> productList = new ArrayList<>();
    private final AtomicLong identity = new AtomicLong(0);
    private Product temp;

    public void add(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
            productList.add(product);
        } else {
            temp = productList.get((int) (product.getId() - 1));
            temp.setTitle(product.getTitle());
            temp.setCost(product.getCost());
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductByID(long id) {
        return productList.get((int) id - 1);
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
