package ru.nik66;

public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
