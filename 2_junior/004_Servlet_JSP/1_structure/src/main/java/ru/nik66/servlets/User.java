package ru.nik66.servlets;

import java.util.Objects;

public class User {

    private final String login;
    private final String email;
    private String password;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.login, user.login) && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.login, this.email, this.password);
    }
}
