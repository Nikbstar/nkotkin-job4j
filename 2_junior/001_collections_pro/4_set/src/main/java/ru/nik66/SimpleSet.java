package ru.nik66;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayList<E> list;

    public SimpleSet() {
        this.list = new SimpleArrayList<>();
    }

    public SimpleSet(int value) {
        this.list = new SimpleArrayList<>(value);
    }

    public int size() {
        return this.list.size();
    }

    public void add(E element) {
        if (!this.list.contains(element)) {
            this.list.add(element);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
