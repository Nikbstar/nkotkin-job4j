package ru.nik66;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

@ThreadSafe
public class SimpleArrayList<E> implements Iterable<E> {

    @GuardedBy("this")
    private Object[] container;
    @GuardedBy("this")
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(final int length) {
        if (length < 0) {
            throw new IllegalArgumentException(String.format("Wrong length: %d", length));
        }
        this.container = new Object[length];
    }

    public SimpleArrayList() {
        this(10);
    }

    public synchronized int size() {
        return this.size;
    }

    synchronized int getLength() {
        return this.container.length;
    }

    public synchronized boolean contains(final E element) {
        boolean result = false;
        for (Object e : this.container) {
            if (element.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public synchronized void add(final E value) {
        this.modCount++;
        if (this.size() >= this.getLength()) {
            increaseArrayLength();
        }
        this.container[this.size++] = value;
    }

    public synchronized E get(final int index) {
        return (E) this.container[index];
    }

    private synchronized void increaseArrayLength() {
        this.container = Arrays.copyOf(this.container, (this.getLength() + 1) + (this.getLength() >> 1));
    }

    @Override
    public synchronized Iterator<E> iterator() {
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
