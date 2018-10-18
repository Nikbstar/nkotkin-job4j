package ru.nik66.tracker.models;

public class Bug extends Item {

    public Bug(String name, String description, long create) {
        super(name, description, create);
    }

    public Bug(String id, String name, String description, long create) {
        super(id, name, description, create);
    }

}
