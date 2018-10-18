package ru.nik66.tracker.models;

public class Task extends Item {

    public Task(String name, String description, long create) {
        super(name, description, create);
    }

    public Task(String id, String name, String description, long create) {
        super(id, name, description, create);
    }

}
