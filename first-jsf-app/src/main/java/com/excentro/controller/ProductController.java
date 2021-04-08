package com.excentro.controller;

import com.excentro.persist.Product;
import com.excentro.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {
  @Inject private ProductRepository productRepository;

  @lombok.Setter @lombok.Getter private Product product;

  private List<Product> productList;

  public void preloadData(ComponentSystemEvent componentSystemEvent) {
    this.productList = productRepository.findAll();
  }

  public List<Product> findAll() {
    return productList;
  }

  public String editProduct(Product product) {
    this.product = product;
    return "/product_form.xhtml?faces-redirect=true";
  }

  public void deleteProduct(Product product) {
    productRepository.delete(product.getId());
  }

  public String saveProduct() {
    productRepository.save(product);
    return "/product.xhtml?faces-redirect=true";
  }

  public String addProduct() {
    this.product = new Product();
    return "/product_form.xhtml?faces-redirect=true";
  }
}
