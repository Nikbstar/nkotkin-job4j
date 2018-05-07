package ru.nik66;

import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {

    private SimpleLinkedList<E> list;

    public SimpleLinkedSet() {
        this.list = new SimpleLinkedList<>();
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
