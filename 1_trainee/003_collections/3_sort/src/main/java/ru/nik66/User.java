package ru.nik66;

public class User implements Comparable<User> {

    private String name;
    private int age;

    @Override
    public int compareTo(User o) {
        return this.getAge() - o.getAge();
    }


    public User(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
