package ru.nik66;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleArrayList<E> implements Iterable<E> {

    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(String.format("Wrong length: %d", length));
        }
        this.container = new Object[length];
    }

    public SimpleArrayList() {
        this(10);
    }

    public int size() {
        return this.size;
    }

    int getLength() {
        return this.container.length;
    }

    public boolean contains(E element) {
        boolean result = false;
        for (Object e : this.container) {
            if (element.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void add(E value) {
        modCount++;
        if (this.size() >= this.getLength()) {
            increaseArrayLength();
        }
        this.container[this.size++] = value;
    }

    public E get(int index) {
        return (E) this.container[index];
    }

    private void increaseArrayLength() {
        this.container = Arrays.copyOf(this.container, (this.getLength() + 1) + (this.getLength() >> 1));
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(this.index++);
            }
        };
    }
}
