package com.excentro.persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id"),
  @NamedQuery(name = "findAllCategories", query = "from Category c"),
  @NamedQuery(name = "countCategories", query = "select count(*) from Category c")
})
public class Category {
  @Id @GeneratedValue private Long id;
  private String name;

  public Category() {}

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
