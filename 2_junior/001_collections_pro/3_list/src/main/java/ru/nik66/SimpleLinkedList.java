package ru.nik66;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {

    @GuardedBy("this")
    private Node<E> first;
    @GuardedBy("this")
    private Node<E> last;
    @GuardedBy("this")
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        this.first = new Node<>(null, null, null);
        this.last = new Node<>(this.first, null, null);
        this.first.setNext(this.last);
    }

    public synchronized boolean contains(final E element) {
        boolean result = false;
        Node<E> node = this.first.getNext();
        while (node != this.last) {
            if (node.getElement().equals(element)) {
                result = true;
                break;
            }
            node = node.getNext();
        }
        return result;
    }

    public synchronized void addFirst(final E value) {
        Node<E> node = this.first;
        node.setElement(value);
        this.first = new Node<>(null, null, node);
        node.setPrev(this.first);
        this.size++;
        this.modCount++;
    }

    public synchronized void addLast(final E value) {
        Node<E> node = this.last;
        node.setElement(value);
        this.last = new Node<>(node, null, null);
        node.setNext(this.last);
        this.size++;
        this.modCount++;
    }

    public synchronized E removeFirst() {
        Node<E> node = this.first.getNext();
        E result = node.getElement();
        this.first.setNext(node.getNext());
        node.getNext().setPrev(this.first);
        this.size--;
        this.modCount++;
        return result;
    }

    public synchronized E removeLast() {
        Node<E> node = this.last.getPrev();
        E result = node.getElement();
        this.last.setPrev(node.getPrev());
        node.getPrev().setNext(this.last);
        this.size--;
        this.modCount++;
        return result;
    }

    public synchronized E remove(final E value) {
        Node<E> node = this.first;
        E result = null;
        for (int i = 0; i < this.size(); i++) {
            node = node.getNext();
            if (node.getElement().equals(value)) {
                result = node.getElement();
                node.getPrev().setNext(node.getNext());
                node.getNext().setPrev(node.getPrev());
                this.size--;
                this.modCount++;
            }
        }
        return result;
    }

    public void add(final E value) {
        this.addLast(value);
    }

    public synchronized E get(final int index) {
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

    public synchronized int size() {
        return this.size;
    }

    @Override
    public synchronized Iterator<E> iterator() {
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

        void setNext(final Node<E> next) {
            this.next = next;
        }

        void setPrev(final Node<E> prev) {
            this.prev = prev;
        }

        void setElement(final E element) {
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
