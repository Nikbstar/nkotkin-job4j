package ru.nik66;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        this.first = new Node<>(null, null, null);
        this.last = new Node<>(this.first, null, null);
        this.first.setNext(this.last);
    }

    public void add(E value) {
        Node<E> tmp = this.last;
        tmp.setElement(value);
        this.last = new Node<>(tmp, null, null);
        tmp.setNext(this.last);
        this.size++;
        this.modCount++;
    }

    public E get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Wrong index!");
        }
        E result;
        if (index < this.size() >> 1) {
            Node<E> node = this.first;
            for (int i = 0; i <= index; i++) {
                node = node.getNext();
            }
            result = node.getElement();
        } else {
            Node<E> node = this.last;
            for (int i = this.size(); i > index; i--) {
                node = node.getPrev();
            }
            result = node.getElement();
        }
        return result;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;
            private int expectedModCount = modCount;
            private Node<E> element = first.getNext();

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = this.element.getElement();
                this.element = this.element.getNext();
                this.index++;
                return result;
            }
        };
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        void setNext(Node<E> next) {
            this.next = next;
        }

        void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        void setElement(E element) {
            this.element = element;
        }

        E getElement() {
            return this.element;
        }

        Node<E> getNext() {
            return this.next;
        }

        Node<E> getPrev() {
            return this.prev;
        }
    }

}
