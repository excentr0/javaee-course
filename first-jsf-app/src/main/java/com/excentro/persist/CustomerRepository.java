package com.excentro.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CustomerRepository {
  private final Map<Long, Customer> customerMap = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong(0);

  @PostConstruct
  public void init() {
    save(new Customer(null, "Customer 1", "000-1111-222"));
    save(new Customer(null, "Customer 2", "000-2222-222"));
    save(new Customer(null, "Customer 3", "000-3333-222"));
    save(new Customer(null, "Customer 4", "000-4444-222"));
  }

  public void save(Customer customer) {
    if (customer.getId() == null) {
      customer.setId(id.incrementAndGet());
    }
    customerMap.put(customer.getId(), customer);
  }

  public void delete(Long id) {
    customerMap.remove(id);
  }

  public Customer findById(Long id) {
    return customerMap.get(id);
  }

  public List<Customer> findAll() {
    return new ArrayList<>(customerMap.values());
  }
}
