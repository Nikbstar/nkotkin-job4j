package ru.nik66.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerTest {

    @Test
    public void addUser() throws ServletException, IOException {
        UsersController controller = new UsersController();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("Nik");
        when(req.getParameter("email")).thenReturn("nnn@nnn");
        controller.doPost(req, resp);
        List<User> actual = UserStorage.getInstance().getUsers();
        User root = new User("root", "root@root", "root");
        User newUser = new User("Nik", "nnn@nnn", null);
        List<User> expected = Arrays.asList(root, newUser);
        assertThat(actual, is(expected));
    }

}