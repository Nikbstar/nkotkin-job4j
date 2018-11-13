package ru.nik66.crudservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder form = new StringBuilder("<form action='").append(req.getContextPath()).append("/user' method='POST'>");
        form.append("<input type='hidden' name='action' value='update' />");
        form.append("<input type='hidden' name='id' value='").append(req.getParameter("id")).append("' />");
        form.append("Name: <input type='text' name='name' value='").append(req.getParameter("name")).append("' /><br />");
        form.append("Login: <input type='text' name='login' value='").append(req.getParameter("login")).append("' /><br />");
        form.append("Email: <input type='text' name='email' value='").append(req.getParameter("email")).append("' /><br />");
        form.append("<input type='submit' value='update' />");
        form.append("</form>");
        PrintWriter print = new PrintWriter(resp.getOutputStream());
        print.append("<!DOCTYPE html>");
        print.append("<html lang='en'>");
        print.append("<head>");
        print.append("<meta charset='UTF-8' />");
        print.append("<title>Update user</title>");
        print.append("</head>");
        print.append("<body>");
        print.append("<h1>Update user</h1>");
        print.append(form.toString());
        print.append("</body>");
        print.append("</html>");
        print.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
