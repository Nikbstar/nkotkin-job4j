package ru.nik66;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] values;
    private int index = 0;

    public SimpleArray(int size) {
        this.values = new Object[size];
    }

    public void add(T model) {
        this.values[this.index++] = model;
    }

    public void set(int index, T model) {
        this.values[index] = model;
    }

    public void delete(int index) {
        this.values[index] = null;
    }

    public T get(int index) {
        return (T) this.values[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return this.cursor != values.length;
            }
            @Override
            public T next() {
                if (this.cursor >= values.length) {
                    throw new NoSuchElementException();
                }
                return (T) values[this.cursor++];
            }
        };
    }
}
