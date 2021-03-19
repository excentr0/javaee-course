package org.excentro.persist;

import java.math.BigDecimal;

public class Product {
  private Long id;
  private String name;
  private String desc;
  private BigDecimal price;

  public Product(Long id, String name, String desc, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.price = price;
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

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
