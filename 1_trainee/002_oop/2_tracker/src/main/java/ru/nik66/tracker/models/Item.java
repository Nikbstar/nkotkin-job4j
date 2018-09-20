package ru.nik66.tracker.models;

import java.util.Random;

public class Item {

    private String id;
    private String name;
    private String description;
    private long create;
    private static final Random RN = new Random();

    public Item(String name, String description, long create) {
        this.setId(String.valueOf(System.currentTimeMillis() + RN.nextInt()));
        this.setName(name);
        this.setDescription(description);
        this.setCreate(create);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreate() {
        return this.create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
