package ru.nik66.crudservlet.model;

public enum City {

    NY("New York"),
    WN("Washington"),
    PARIS("Paris"),
    MILAN("Milan");

    private String desc;

    City(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return this.name();
    }

}
