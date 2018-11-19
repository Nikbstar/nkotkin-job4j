package ru.nik66.crudservlet.servlet;

import ru.nik66.crudservlet.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {

    private static final String UPDATE = "/WEB-INF/views/update.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sessionRole", req.getSession().getAttribute("role"));
        req.setAttribute("roles", Role.values());
        req.setAttribute("id", req.getParameter("id"));
        req.setAttribute("name", req.getParameter("name"));
        req.setAttribute("login", req.getParameter("login"));
        req.setAttribute("password", req.getParameter("password"));
        req.setAttribute("role", req.getParameter("role"));
        req.setAttribute("email", req.getParameter("email"));
        req.getRequestDispatcher(UPDATE).forward(req, resp);
    }
}
