package com.excentro.persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
  @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
  @NamedQuery(name = "findAllProducts", query = "from Product p"),
  @NamedQuery(name = "count", query = "select count(*) from Product p")
})
public class Product {
  @Id @GeneratedValue private Long id;

  private String name;
  private String description;
  private BigDecimal price;

  public Product() {}

  public Product(Long id, String name, String desc, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.description = desc;
    this.price = price;
  }

  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Product product = (Product) o;

    return getId() != null ? getId().equals(product.getId()) : product.getId() == null;
  }

  @Override
  public String toString() {
    return "Product{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", desc='"
        + description
        + '\''
        + ", price="
        + price
        + '}';
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String desc) {
    this.description = desc;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
