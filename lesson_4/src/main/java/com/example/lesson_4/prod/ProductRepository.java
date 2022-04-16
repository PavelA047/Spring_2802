package com.example.lesson_4.prod;

import com.example.lesson_4.persist.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findUserByTitleLike(@Param("title") String title);

    List<Product> findByCostGreaterThanEqual(@Param("cost") Long cost);

    List<Product> findByCostLessThanEqual(@Param("cost") Long cost);

    List<Product> findByCostBetween(@Param("productMinFilter") Long minCost, @Param("productMaxFilter") Long maxCost);

    @Query("select p from Product p where p.title like :title and p.cost between :minCost and :maxCost")
    List<Product> findByTitleAndCostBetweenMinAndMax(@Param("title") String title, @Param("minCost") Long minCost, @Param("maxCost") Long maxCost);

    @Query("select p from Product p where p.title like :title and p.cost >= :minCost")
    List<Product> findByTitleAndCostGreaterThenEqual(@Param("title") String title, @Param("minCost") Long minCost);

    @Query("select p from Product p where p.title like :title and p.cost <= :maxCost")
    List<Product> findByTitleAndCostLessThenEqual(@Param("title") String title, @Param("maxCost") Long maxCost);

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<Product> findAll() {
//        return entityManager.createQuery("select p from Product p", Product.class)
//                .getResultList();
//    }
//
//    public Product getProductByID(long id) {
//        return entityManager.find(Product.class, id);
//    }
//
//    @Transactional
//    public Product save(Product product) {
//        if (product.getId() == null) {
//            entityManager.persist(product);
//        } else entityManager.merge(product);
//        return product;
//    }
//
//    @Transactional
//    public void delete(long id) {
//        entityManager.remove(entityManager.find(Product.class, id));
//    }
}
