package com.example.product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ProductDao {

    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
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
        }
    }
}
