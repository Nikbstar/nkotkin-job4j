package ru.nik66.tracker.start;

import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {

    private final int key;
    private final String name;
    final Consumer<String> media;

    protected BaseAction(int key, String name, Consumer<String> media) {
        this.key = key;
        this.name = name;
        this.media = media;
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
