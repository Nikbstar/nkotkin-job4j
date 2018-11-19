package ru.nik66.crudservlet.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private int id;
    private final String name;
    private final String login;
    private final String email;
    private LocalDateTime createDate;

    public User(String name, String login, String email, LocalDateTime createDate) {
        this(0, name, login, email, createDate);
    }

    public User(int id, String name, String login, String email, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
        return this.id == user.id
                && Objects.equals(this.name, user.name)
                && Objects.equals(this.login, user.login)
                && Objects.equals(this.email, user.email)
                && Objects.equals(this.createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.login, this.email, this.createDate);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + '\'' + ", login='" + login + '\'' + ", email='" + email + '\'' + ", createDate=" + createDate + '}';
    }
}
