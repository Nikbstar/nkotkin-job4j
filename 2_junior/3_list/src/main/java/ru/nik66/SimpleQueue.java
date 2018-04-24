package ru.nik66;

public class SimpleQueue<T> {

    private SimpleLinkedList<T> list = new SimpleLinkedList<>();

    public T poll() {
        return this.list.removeFirst();
    }

    public void push(T value) {
        this.list.addLast(value);
    }

}
