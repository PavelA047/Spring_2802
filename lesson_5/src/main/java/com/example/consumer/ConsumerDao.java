package com.example.consumer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ConsumerDao {

    EntityManager entityManager;

    public ConsumerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Consumer findById(Long id) {
        return entityManager.find(Consumer.class, id);
    }

    public List<Consumer> findAll() {
        return entityManager.createQuery("select c from Consumer c", Consumer.class)
                .getResultList();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Consumer.class, id));
        entityManager.getTransaction().commit();
    }

    public void saveOrUpdate(Consumer consumer) {
        try {
            Consumer con = entityManager.createQuery("select c from Consumer c where c.name = :name", Consumer.class)
                    .setParameter("name", consumer.getName())
                    .getSingleResult();
            consumer.setId(con.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(consumer);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            entityManager.getTransaction().begin();
            entityManager.persist(consumer);
            entityManager.getTransaction().commit();
        }
    }
}
