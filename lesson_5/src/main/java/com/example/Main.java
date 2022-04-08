package com.example;

import com.example.product.model.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Insert
//        entityManager.persist(new Product("Product 1", 1100L));
//        entityManager.persist(new Product("Product 2", 1200L));
//        entityManager.persist(new Product("Product 3", 1300L));

        //Select
//        System.out.println(entityManager.find(Product.class, 3L));

        //Select with HQL/JPQL
//        List<Product> l = entityManager.createQuery("select p from Product p", Product.class)
//                .getResultList();
//        l.forEach(System.out::println);

        //update 1
//        Product product = entityManager.find(Product.class, 1L);
//        product.setTitle("Pro 11111111111");

        //update 2
//        Product product = new Product("qqqqqqq", 1700L);
//        product.setId(2L);
//        entityManager.merge(product);

        //delete
//        entityManager.remove(entityManager.find(Product.class, 7L));
//        entityManager.createQuery("delete from Product p where p.id = 6L")
//                .executeUpdate();




        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
