package org.excentro;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Http Servlet", urlPatterns = "/http-servlet/*")
public class FirstHTTPServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    resp.getWriter().println("Hello from HTTP Sevlet");

    resp.getWriter().println("<p>" + req.getContextPath() + "</p>");
    resp.getWriter().println("<p>" + req.getServletPath() + "</p>");
    resp.getWriter().println("<p>" + req.getParameter("User-Agent") + "</p>");
  }
}
