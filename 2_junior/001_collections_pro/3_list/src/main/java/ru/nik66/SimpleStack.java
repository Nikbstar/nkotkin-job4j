package ru.nik66;

public class SimpleStack<T> {

    private SimpleLinkedList<T> list = new SimpleLinkedList<>();

    public T poll() {
        return this.list.removeLast();
    }

    public void push(T value) {
        this.list.addLast(value);
    }

}
