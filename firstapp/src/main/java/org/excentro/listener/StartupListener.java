package org.excentro.listener;

import org.excentro.persist.CategoryRepository;
import org.excentro.persist.CustomerRepository;
import org.excentro.persist.Product;
import org.excentro.persist.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class StartupListener implements ServletContextListener {
  private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("Startup Listener");

    ProductRepository productRepository = new ProductRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    CustomerRepository customerRepository = new CustomerRepository();

    productRepository.save(new Product(null, "Product 1", "Description 1", new BigDecimal(1)));
    productRepository.save(new Product(null, "Product 2", "Description 2", new BigDecimal(2)));
    productRepository.save(new Product(null, "Product 3", "Description 3", new BigDecimal(3)));
    productRepository.save(new Product(null, "Product 4", "Description 4", new BigDecimal(4)));

    sce.getServletContext().setAttribute("productRepository", productRepository);
    sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
    sce.getServletContext().setAttribute("customerRepository", customerRepository);
  }
}
