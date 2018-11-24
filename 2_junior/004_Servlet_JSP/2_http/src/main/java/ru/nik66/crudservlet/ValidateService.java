package ru.nik66.crudservlet;

import ru.nik66.crudservlet.model.Role;
import ru.nik66.crudservlet.model.User;
import ru.nik66.crudservlet.store.DbStore;
import ru.nik66.crudservlet.store.MemoryStore;
import ru.nik66.crudservlet.store.Store;

import java.time.LocalDateTime;
import java.util.List;

public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

//    private final Store persistent = DbStore.getInstance();
    private final Store persistent = MemoryStore.getInstance();

    private ValidateService() {
        persistent.add(new User("root", "root", "root", Role.ADMIN, "root@email.ru", LocalDateTime.now()));
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private boolean isWrongEmail(String email) {
        return !email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    private void checkLoginContent(int id, String login) {
        for (User user : this.persistent.findAll()) {
            if (user.getId() != id && user.getLogin().equals(login)) {
                throw new IllegalArgumentException("This login is used!");
            }
        }
    }

    private void checkEmailContent(int id, String email) {
        for (User user : this.persistent.findAll()) {
            if (user.getId() != id && user.getEmail().equals(email)) {
                throw new IllegalArgumentException("This email is used!");
            }
        }
    }

    @Override
    public boolean add(String name, String login, String password, String role, String email) {
        if (this.isWrongEmail(email)) {
            throw new IllegalArgumentException("Wrong email address!");
        }
        checkLoginContent(0, login);
        checkEmailContent(0, email);
        boolean result = false;
        User user = new User(name, login, password, Role.valueOf(role), email, LocalDateTime.now());
        if (!this.persistent.findAll().contains(user)) {
            this.persistent.add(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean update(int id, String name, String login, String password, String role, String email) {
        if (this.isWrongEmail(email)) {
            throw new IllegalArgumentException("Wrong email address!");
        }
        checkLoginContent(id, login);
        checkEmailContent(id, email);
        boolean result = false;
        User oldUser = this.persistent.findById(id);
        if (oldUser != null) {
            User user = new User(id, name, login, password, Role.valueOf(role), email, oldUser.getCreateDate());
            this.persistent.update(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        User user = this.persistent.findById(id);
        if (user != null) {
            this.persistent.delete(user);
            result = true;
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return this.persistent.findAll();
    }

    @Override
    public User findById(int id) {
        return this.persistent.findById(id);
    }

    @Override
    public void clear() {
        this.persistent.clear();
    }

    @Override
    public Role checkContainAndReturnRole(String login, String password) {
        Role result = null;
        for (User user : this.persistent.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
                break;
            }
        }
        return result;
    }
}
