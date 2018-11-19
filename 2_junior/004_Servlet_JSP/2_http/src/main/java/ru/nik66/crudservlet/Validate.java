package ru.nik66.crudservlet;

import ru.nik66.crudservlet.model.Role;
import ru.nik66.crudservlet.model.User;

import java.util.List;

public interface Validate {

    boolean add(String name, String login, String password, String role, String email);
    boolean update(int id, String name, String login, String password, String role, String email);
    boolean delete(int id);
    List<User> findAll();
    User findById(int id);
    void clear();
    Role checkContainAndReturnRole(String login, String password);
}
