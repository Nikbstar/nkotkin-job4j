package ru.nik66.crudservlet.servlet;

import ru.nik66.crudservlet.Validate;
import ru.nik66.crudservlet.ValidateService;
import ru.nik66.crudservlet.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private static final String AUTH = "WEB-INF/views/auth.jsp";
    private final Validate logic = ValidateService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AUTH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = this.logic.checkContainAndReturnRole(login, password);
        if (role != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            req.setAttribute("error", "Wrong login!");
            this.doGet(req, resp);
        }
    }
}
