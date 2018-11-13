package ru.nik66.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EchoServlet extends HttpServlet {

    private List<String> names = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (String name : this.names) {
            sb.append("<tr><td>").append(name).append("</td></tr>");
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>");
        writer.append("<html lang='en'>");
        writer.append("<head>");
        writer.append("<meta charset='UTF-8' />");
        writer.append("<title>Echo Servlet</title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append("<form action='").append(req.getContextPath()).append("/echo' method='POST'>");
        writer.append("Name: <input type='text' name='name'>").append("<br />");
        writer.append("<input type='submit' />");
        writer.append("</form>").append("<hr />");
        writer.append(sb.toString());
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.names.add(req.getParameter("name"));
        doGet(req, resp);
    }
}
