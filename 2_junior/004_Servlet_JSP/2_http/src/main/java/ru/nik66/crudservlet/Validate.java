package ru.nik66.crudservlet;

import ru.nik66.crudservlet.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface Validate {

    boolean add(String name, String login, String email);
    boolean update(int id, String name, String login, String email);
    boolean delete(int id);
    List<User> findAll();
    User findById(int id);

}
