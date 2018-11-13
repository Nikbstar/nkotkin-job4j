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

public class UsersServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<User> users = this.logic.findAll();
        StringBuilder table = new StringBuilder("<table>");
        table.append("<td><tr><form action='").append(req.getContextPath()).append("/create' method='POST'>");
        table.append("<input type='submit' value='create' />");
        table.append("</form></tr></td>");
        for (User user : users) {
            table.append("<td>");
            table.append("<tr>").append(user.getId()).append(") ").append(user.getLogin()).append("<tr>");
            table.append("<tr><form action='").append(req.getContextPath()).append("/update' method='POST'>");
            table.append("<input type='hidden' name='id' value='").append(user.getId()).append("' />");
            table.append("<input type='hidden' name='name' value='").append(user.getName()).append("' />");
            table.append("<input type='hidden' name='login' value='").append(user.getLogin()).append("' />");
            table.append("<input type='hidden' name='login' value='").append(user.getLogin()).append("' />");
            table.append("<input type='hidden' name='email' value='").append(user.getEmail()).append("' />");
            table.append("<input type='hidden' name='date' value='").append(user.getCreateDate()).append("' />");
            table.append("<input type='submit' value='update' />");
            table.append("</form></tr>");
            // TODO servlet for deleting
            table.append("<tr><form action='").append(req.getContextPath()).append("/delete' method='POST'>");
            table.append("<input type='hidden' name='id' value='").append(user.getId()).append("' />");
            table.append("<input type='submit' value='delete' />");
            table.append("</form></tr>");
            table.append("</td>");
        }
        table.append("</table>");
        PrintWriter print = new PrintWriter(resp.getOutputStream());
        print.append("<!DOCTYPE html>");
        print.append("<html lang='en'>");
        print.append("<head>");
        print.append("<meta charset='UTF-8' />");
        print.append("<title>Users list</title>");
        print.append("</head>");
        print.append("<body>");
        print.append(table.toString());
        print.append("</body>");
        print.append("</html>");
        print.flush();
    }

}
