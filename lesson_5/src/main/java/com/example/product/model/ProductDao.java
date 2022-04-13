package com.example.product.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class ProductDao {

    private final EntityManagerFactory entityManagerFactory;

    public ProductDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Product findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Product.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Product> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("select p from Product p", Product.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Product.class, id));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void saveOrUpdate(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Product prod = entityManager.createQuery("select p from Product p where p.title = :title", Product.class)
                    .setParameter("title", product.getTitle())
                    .getSingleResult();
            product.setId(prod.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
//        List<Product> products = findAll();
//        if (products.size() != 0) {
//            for (Product prod : products) {
//                if (prod.getTitle().equals(product.getTitle())) {
//                    product.setId(prod.getId());
//                    entityManager.getTransaction().begin();
//                    entityManager.merge(product);
//                    entityManager.getTransaction().commit();
//                    return;
//                }
//            }
//        }
//        entityManager.getTransaction().begin();
//        entityManager.persist(product);
//        entityManager.getTransaction().commit();
    }
}
