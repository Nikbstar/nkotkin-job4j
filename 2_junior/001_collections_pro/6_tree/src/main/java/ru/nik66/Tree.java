package ru.nik66;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    public int size;
    private int modCount;

    public Tree(E e) {
        this.root = new Node<>(e);
        this.size++;
        this.modCount++;
    }

    public int size() {
        return this.size;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> el = this.findBy(parent);
        boolean result = el.isPresent();
        if (result) {
            el.get().add(new Node<>(child));
            this.size++;
            this.modCount++;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Queue<Node<E>> data = new LinkedList<>();
            private int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                if (this.index >= size()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Node<E> el;
                if (this.index == 0) {
                    this.data.offer(root);
                }
                el = this.data.poll();
                this.index++;
                for (Node<E> child : el.leaves()) {
                    this.data.offer(child);
                }
                return el.getValue();
            }
        };
    }

}
