package org.excentro;

import org.excentro.persist.Product;
import org.excentro.persist.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
  private ProductRepository productRepository;

  @Override
  public void init() {
    productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.getWriter().println("<p>" + req.getRequestURI() + "</p>");

    resp.getWriter().println("<table>");
    resp.getWriter().println("<tr>");
    resp.getWriter().println("<th>Id</th>");
    resp.getWriter().println("<th>Name</th>");
    resp.getWriter().println("<th>Description</th>");
    resp.getWriter().println("<th>Price</th>");
    resp.getWriter().println("</tr>");

    for (Product product : productRepository.findAll()) {
      resp.getWriter().println("<tr>");
      resp.getWriter()
          .println(
              "<td><a href='"
                  + getServletContext().getContextPath()
                  + "/product?id="
                  + product.getId()
                  + "'>"
                  + product.getId()
                  + "</td>");
      resp.getWriter().println("<td>" + product.getName() + "</td>");
      resp.getWriter().println("<td>" + product.getDesc() + "</td>");
      resp.getWriter().println("<td>" + product.getPrice() + "</td>");
      resp.getWriter().println("</tr>");
    }
    resp.getWriter().println("</table>");
  }
}
