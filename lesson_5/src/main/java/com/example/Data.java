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

        Consumer consumer = entityManager.find(Consumer.class, 10L);
        consumer.getProductList().add(entityManager.find(Product.class, 106L));
        consumer.getProductList().add(entityManager.find(Product.class, 105L));
        consumer.getProductList().add(entityManager.find(Product.class, 104L));

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
