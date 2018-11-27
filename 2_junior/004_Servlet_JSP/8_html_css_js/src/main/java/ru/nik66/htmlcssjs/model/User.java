package ru.nik66.htmlcssjs.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({ "id", "fname", "lname", "sex", "desc" })
public class User {

    private int id;
    private String fname;
    private String lname;
    private Sex sex;
    private String desc;

    public User() {
    }

    public User(final int id, final String fname, final String lname, final Sex sex, final String desc) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.sex = sex;
        this.desc = desc;
    }

    public int getId() {
        return this.id;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public Sex getSex() {
        return this.sex;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return id == user.id && Objects.equals(fname, user.fname) && Objects.equals(lname, user.lname) && sex == user.sex && Objects.equals(desc, user.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fname, lname, sex, desc);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fname='" + fname + '\'' + ", lname='" + lname + '\'' + ", sex=" + sex + ", desc='" + desc + '\'' + '}';
    }
}
