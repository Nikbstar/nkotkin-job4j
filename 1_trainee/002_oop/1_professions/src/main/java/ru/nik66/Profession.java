package ru.nik66;

public class Profession {

    private String name;
    private int age;

    public Profession(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

}
