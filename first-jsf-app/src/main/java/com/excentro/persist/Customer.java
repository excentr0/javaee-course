package com.excentro.persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "deleteCustomerById", query = "delete from Customer c where c.id = :id"),
  @NamedQuery(name = "findAllCustomers", query = "from Customer c"),
  @NamedQuery(name = "countCustomers", query = "select count(*) from Customer c")
})
public class Customer {
  @Id @GeneratedValue private Long id;
  private String name;
  private String phone;

  public Customer() {}

  public Customer(Long id, String name, String phone) {
    this.id = id;
    this.name = name;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
