package ru.nik66.crudservlet.store;

import ru.nik66.crudservlet.model.User;

import java.util.List;

public interface Store {

    void add(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findById(int id);

    void clear();

}
