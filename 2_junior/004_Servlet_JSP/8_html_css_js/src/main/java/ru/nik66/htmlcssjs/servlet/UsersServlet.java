package ru.nik66.htmlcssjs.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nik66.htmlcssjs.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UsersServlet extends HttpServlet {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String json = new ObjectMapper().writeValueAsString(this.users.values());
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User jUser = new ObjectMapper().readValue(req.getReader().readLine(), User.class);
        jUser.setId(generateId());
        this.users.put(jUser.getId(), jUser);
    }

    private int generateId() {
        return this.id.incrementAndGet();
    }

}
