package ru.nik66.crudservlet.servlet;

import ru.nik66.crudservlet.Validate;
import ru.nik66.crudservlet.ValidateService;
import ru.nik66.crudservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        Map<String, String[]> params = req.getParameterMap();
        boolean result = new ActionType(params, this.logic).init().doAction(action);
        if (!result) {
            throw new ServletException("Something wrong!");
        }
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }
}
