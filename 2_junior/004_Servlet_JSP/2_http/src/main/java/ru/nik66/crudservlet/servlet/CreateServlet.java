package ru.nik66.crudservlet.servlet;

import ru.nik66.crudservlet.model.Country;
import ru.nik66.crudservlet.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServlet extends HttpServlet {

    private static final String CREATE = "/WEB-INF/views/create.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.setAttribute("countries", Country.values());
        req.getRequestDispatcher(CREATE).forward(req, resp);
    }
}
