package ru.nik66.crudservlet.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nik66.crudservlet.model.City;
import ru.nik66.crudservlet.model.Country;
import ru.nik66.crudservlet.store.CountryStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CountryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        Map<String, String> forJson = new ConcurrentHashMap<>();
        for (City city : CountryStore.getInstance().getCities(Country.valueOf(req.getParameter("country")))) {
            forJson.put(city.getName(), city.getDesc());
        }
        String json = new ObjectMapper().writeValueAsString(forJson);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }
}
