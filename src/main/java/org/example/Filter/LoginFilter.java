package org.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.getParameterMap().forEach((k,v)-> System.out.println(k+" : "+ Arrays.toString(v)));
        chain.doFilter(request,response);
    }
}
