package com.excentro.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {
  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  @Resource private UserTransaction ut;

  @PostConstruct
  public void init() {
    if (count() == 0) {
      try {
        ut.begin();
        save(new Product(null, "Product 1", "Description 1", new BigDecimal(1)));
        save(new Product(null, "Product 2", "Description 2", new BigDecimal(2)));
        save(new Product(null, "Product 3", "Description 3", new BigDecimal(3)));
        save(new Product(null, "Product 4", "Description 4", new BigDecimal(4)));
        ut.commit();
      } catch (NotSupportedException
          | SystemException
          | HeuristicRollbackException
          | RollbackException
          | HeuristicMixedException e) {
        e.printStackTrace();
        try {
          ut.rollback();
        } catch (SystemException systemException) {
          systemException.printStackTrace();
        }
      }
    }
  }

  public long count() {
    return em.createNamedQuery("count", Long.class).getSingleResult();
  }

  @Transactional
  public void save(Product product) {
    if (product.getId() == null) {
      em.persist(product);
    }
    em.merge(product);
  }

  @Transactional
  public void delete(Long id) {
    em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
  }

  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  public List<Product> findAll() {
    return em.createNamedQuery("findAllProducts", Product.class).getResultList();
  }
}
