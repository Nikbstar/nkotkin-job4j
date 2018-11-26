package ru.nik66.crudservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, String[]> params = req.getParameterMap();
        boolean result = new ActionType(params).init().doAction(params.get("action")[0]);
        if (!result) {
            throw new ServletException("Something wrong!");
        }
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }

}
