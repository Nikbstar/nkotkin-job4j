package ru.nik66.servlets;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {

    private static final UserStorage INSTANCE = new UserStorage();

    private List<User> users = new CopyOnWriteArrayList<>();

    public void add(User user) {
        this.users.add(user);
    }

    public boolean isCredential(String login, String password) {
        boolean result = false;
        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                result = true;
                break;
            }
        }
        return result;
    }

    private UserStorage() {
        this.users.add(new User("root", "root@root", "root"));
    }

    public List<User> getUsers() {
        return this.users;
    }

    public static UserStorage getInstance() {
        return INSTANCE;
    }
}
