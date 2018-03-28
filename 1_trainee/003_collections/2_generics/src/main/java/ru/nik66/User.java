package ru.nik66;

public class User {

    private int id;
    private String name;
    private String city;

    public User(int id, String name, String city) {
        setId(id);
        setName(name);
        setCity(city);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
