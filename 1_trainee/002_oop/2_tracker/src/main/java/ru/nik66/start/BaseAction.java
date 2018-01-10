package ru.nik66.start;

public abstract class BaseAction implements UserAction {

    private final int key;
    private final String name;

    protected BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%d. %s", this.key, this.name);
    }
}
