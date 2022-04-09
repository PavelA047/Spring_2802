package com.example.product.model;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {

    private static final EntityManagerFactory entityManagerFactory;
    private static final EntityManager entityManager;

    static {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        entityManager = entityManagerFactory.createEntityManager();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Product.class, id));
        entityManager.getTransaction().commit();
    }

    public void saveOrUpdate(Product product) {
        List<Product> products = findAll();
        if (products.size() != 0) {
            for (Product prod : products) {
                if (prod.getTitle().equals(product.getTitle())) {
                    product.setId(prod.getId());
                    entityManager.getTransaction().begin();
                    entityManager.merge(product);
                    entityManager.getTransaction().commit();
                    return;
                }
            }
        }
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
