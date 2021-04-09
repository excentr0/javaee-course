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
public class CustomerRepository {
  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  @Resource private UserTransaction ut;

  @PostConstruct
  public void init() {
    if (count() == 0) {
      try {
        ut.begin();
        save(new Customer(null, "Customer 1", "000-1111-222"));
        save(new Customer(null, "Customer 2", "000-2222-222"));
        save(new Customer(null, "Customer 3", "000-3333-222"));
        save(new Customer(null, "Customer 4", "000-4444-222"));
        ut.commit();
      } catch (NotSupportedException
          | SystemException
          | RollbackException
          | HeuristicMixedException
          | HeuristicRollbackException e) {
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
    return em.createNamedQuery("countCustomers", Long.class).getSingleResult();
  }

  @Transactional
  public void save(Customer customer) {
    if (customer.getId() == null) {
      em.persist(customer);
    }
    em.merge(customer);
  }

  @Transactional
  public void delete(Long id) {
    em.createNamedQuery("deleteCustomerById").setParameter("id", id).executeUpdate();
  }

  public Customer findById(Long id) {
    return em.find(Customer.class, id);
  }

  public List<Customer> findAll() {
    return em.createNamedQuery("findAllCustomers", Customer.class).getResultList();
  }
}
