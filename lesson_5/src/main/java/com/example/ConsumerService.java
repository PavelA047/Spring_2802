package com.example;

import com.example.consumer.Consumer;
import com.example.consumer.ConsumerDao;
import com.example.product.Product;
import com.example.product.ProductDao;

import java.util.List;

public class ConsumerService {
    ConsumerDao consumerDao;
    ProductDao productDao;

    public ConsumerService(ConsumerDao consumerDao, ProductDao productDao) {
        this.consumerDao = consumerDao;
        this.productDao = productDao;
    }

    public List<Product> getProductListByConsumerId(Long consumer_id) {
        Consumer consumer = consumerDao.findById(consumer_id);
        return consumer.getProductList();
    }

    public List<Consumer> getConsumerListByProductId(Long product_id) {
        Product product = productDao.findById(product_id);
        return product.getConsumerList();
    }
}
