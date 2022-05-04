package com.example;

import com.example.consumer.ConsumerDao;
import com.example.product.ProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("com.example")
public class AppConf {

    EntityManagerFactory entityManagerFactory = new org.hibernate.cfg.Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Bean
    public ConsumerService consumerService(ConsumerDao consumerDao, ProductDao productDao) {
        return new ConsumerService(consumerDao, productDao);
    }

    @Bean
    public ConsumerDao consumerDao() {
        return new ConsumerDao(entityManager);
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDao(entityManager);
    }

    @PreDestroy
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
