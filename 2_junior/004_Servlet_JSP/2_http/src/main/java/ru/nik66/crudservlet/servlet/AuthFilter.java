package ru.nik66.crudservlet.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (!req.getRequestURI().contains("/auth")) {
            HttpSession session = req.getSession();
            if (session.getAttribute("login") == null) {
                resp.sendRedirect(String.format("%s/auth", req.getContextPath()));
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }

}
