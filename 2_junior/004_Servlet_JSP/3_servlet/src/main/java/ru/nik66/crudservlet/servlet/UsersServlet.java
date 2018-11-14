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
        String createUserForm = String.format("<form action='%s/create' method='GET'><input type='submit' value='create' /></form>", req.getContextPath());
        StringBuilder table = new StringBuilder("<table>");
        for (User user : users) {
            table.append("<tr>");
            table.append("<td>").append(user.getId()).append(") ").append(user.getLogin()).append("</td>");
            table.append("<td><form action='").append(req.getContextPath()).append("/update' method='POST'>");
            table.append("<input type='hidden' name='id' value='").append(user.getId()).append("' />");
            table.append("<input type='hidden' name='name' value='").append(user.getName()).append("' />");
            table.append("<input type='hidden' name='login' value='").append(user.getLogin()).append("' />");
            table.append("<input type='hidden' name='login' value='").append(user.getLogin()).append("' />");
            table.append("<input type='hidden' name='email' value='").append(user.getEmail()).append("' />");
            table.append("<input type='submit' value='update' />");
            table.append("</form></td>");
            table.append("<td><form action='").append(req.getContextPath()).append("/user' method='POST'>");
            table.append("<input type='hidden' name='id' value='").append(user.getId()).append("' />");
            table.append("<input type='hidden' name='action' value='delete' />");
            table.append("<input type='submit' value='delete' />");
            table.append("</form></td>");
            table.append("</tr>");
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
        print.append("<h1>Users list</h1>");
        print.append(createUserForm);
        print.append(table.toString());
        print.append("</body>");
        print.append("</html>");
        print.flush();
    }

}
