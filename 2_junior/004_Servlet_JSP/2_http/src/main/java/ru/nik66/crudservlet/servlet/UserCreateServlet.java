package ru.nik66.crudservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder form = new StringBuilder("<form action='").append(req.getContextPath()).append("/user' method='POST'>");
        form.append("<input type='hidden' name='action' value='add' />");
        form.append("Name: <input type='text' name='name' /><br />");
        form.append("Login: <input type='text' name='login' /><br />");
        form.append("Email: <input type='text' name='email' /><br />");
        form.append("<input type='submit' value='create' />");
        form.append("</form>");
        PrintWriter print = new PrintWriter(resp.getOutputStream());
        print.append("<!DOCTYPE html>");
        print.append("<html lang='en'>");
        print.append("<head>");
        print.append("<meta charset='UTF-8' />");
        print.append("<title>Create user</title>");
        print.append("</head>");
        print.append("<body>");
        print.append("<h1>Create user</h1>");
        print.append(form.toString());
        print.append("</body>");
        print.append("</html>");
        print.flush();
    }

}
