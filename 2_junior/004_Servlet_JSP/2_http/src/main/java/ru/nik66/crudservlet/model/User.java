package ru.nik66.crudservlet.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private int id;
    private final String name;
    private final String login;
    private final String password;
    private final Role role;
    private final String email;
    private LocalDateTime createDate;
    private Country country;
    private City city;

    public User(String name, String login, String password, Role role, String email, LocalDateTime createDate, Country country, City city) {
        this(0, name, login, password, role, email, createDate, country, city);
    }

    public User(int id, String name, String login, String password, Role role, String email, LocalDateTime createDate, Country country, City city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.createDate = createDate;
        this.country = country;
        this.city = city;
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

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role && Objects.equals(email, user.email) && Objects.equals(createDate, user.createDate) && country == user.country && city == user.city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, role, email, createDate, country, city);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", login='" + login + '\'' + ", password='" + password + '\'' + ", role=" + role + ", email='" + email + '\'' + ", createDate=" + createDate + ", country=" + country + ", city=" + city + "}";
    }
}
