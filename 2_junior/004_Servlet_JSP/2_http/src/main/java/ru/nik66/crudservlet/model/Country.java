package ru.nik66.crudservlet.model;

public enum Country {

    USA("United States of America"),
    FR("France");

    private String desc;

    Country(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return this.name();
    }

}
