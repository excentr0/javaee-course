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
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {
  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  @Resource private UserTransaction ut;

  @PostConstruct
  public void init() {

    if (count() == 0) {
      try {
        ut.begin();
        save(new Category(null, "Category 1"));
        save(new Category(null, "Category 2"));
        save(new Category(null, "Category 3"));
        save(new Category(null, "Category 4"));
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
    return em.createNamedQuery("countCategories", Long.class).getSingleResult();
  }

  @Transactional
  public void save(Category category) {
    if (category.getId() == null) {
      em.persist(category);
    }
    em.merge(category);
  }

  @Transactional
  public void delete(Long id) {
    em.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
  }

  public Category findById(Long id) {
    return em.find(Category.class, id);
  }

  public List<Category> findAll() {
    return em.createNamedQuery("findAllCategories", Category.class).getResultList();
  }
}
