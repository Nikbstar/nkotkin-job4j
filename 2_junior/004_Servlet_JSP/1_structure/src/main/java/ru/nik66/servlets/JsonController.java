package ru.nik66.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (User user : UserStorage.getInstance().getUsers()) {
            sb.append("{\"login\": \"").append(user.getLogin()).append("\", \"email\": \"").append(user.getEmail()).append("\"}, ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(sb.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStorage.getInstance().add(new User(req.getParameter("login"), req.getParameter("email"), null));
    }
}
