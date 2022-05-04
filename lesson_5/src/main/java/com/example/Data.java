package com.example;

import com.example.consumer.Consumer;
import com.example.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Data {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Consumer consumer = entityManager.find(Consumer.class, 15L);
        consumer.getProductList().add(entityManager.find(Product.class, 108L));
        consumer.getProductList().add(entityManager.find(Product.class, 109L));
        consumer.getProductList().add(entityManager.find(Product.class, 110L));

        consumer = entityManager.find(Consumer.class, 16L);
        consumer.getProductList().add(entityManager.find(Product.class, 108L));
        consumer.getProductList().add(entityManager.find(Product.class, 110L));

        consumer = entityManager.find(Consumer.class, 17L);
        consumer.getProductList().add(entityManager.find(Product.class, 109L));

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
