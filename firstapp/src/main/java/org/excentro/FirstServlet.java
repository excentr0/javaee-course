package org.excentro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class FirstServlet implements Servlet {
  private static final Logger log = LoggerFactory.getLogger(FirstServlet.class);
  private ServletConfig servletConfig;

  @Override
  public void init(ServletConfig servletConfig) {
    this.servletConfig = servletConfig;
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.servletConfig;
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws IOException {
    log.info("New request");
    servletResponse.getWriter().println("<h1>Hello from Servlet</h1>");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {}
}
