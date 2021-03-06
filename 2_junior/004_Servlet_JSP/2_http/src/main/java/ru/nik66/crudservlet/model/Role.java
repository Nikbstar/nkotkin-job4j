package ru.nik66.crudservlet.model;

public enum Role {

    ADMIN("Administrator"),
    USER("User");

    private String desc;

    Role(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return this.name();
    }

}
