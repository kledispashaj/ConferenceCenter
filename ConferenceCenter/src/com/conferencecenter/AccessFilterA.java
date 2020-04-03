package com.conferencecenter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AccessFilterA implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    try {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession session = httpRequest.getSession(false);

      boolean isLoggedIn = (session != null && session.getAttribute("currentSessionUser")!=null && ((UserObj)session.getAttribute("currentSessionUser")).getRolID()==1);

      if (!isLoggedIn) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("AccessDenied.html");
        dispatcher.forward(request, response);
      }

      else
          chain.doFilter(request, response);

    }
catch (Throwable theException)
    {
        System.out.println("error filtri "+ theException);
    }
  }

    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}