package ru.nik66.crudservlet.servlet;

import ru.nik66.crudservlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersListServlet extends HttpServlet {

    private final static String LIST = "/WEB-INF/views/list.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        req.setAttribute("sessionLogin", req.getSession().getAttribute("login"));
        req.setAttribute("sessionRole", req.getSession().getAttribute("role"));
        req.getRequestDispatcher(LIST).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit").equals("yes")) {
            req.getSession().invalidate();
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        }
    }
}
