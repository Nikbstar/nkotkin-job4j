package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class NodeTest {
    Node<Integer> first;
    Node<Integer> two;
    Node<Integer> third;
    Node<Integer> four;

    @Before
    public void init() {
        this.first = new Node<>(1);
        this.two = new Node<>(2);
        this.third = new Node<>(3);
        this.four = new Node<>(4);
    }

    @Test
    public void whenNodesHasCycleThenTrue() {
        this.first.setNext(this.two);
        this.two.setNext(this.third);
        this.third.setNext(this.four);
        this.four.setNext(this.first);

        boolean actual = Node.hasCycle(this.first);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenNodesHasCycleInMiddleThenTrue() {
        this.first.setNext(this.two);
        this.two.setNext(this.first);
        this.third.setNext(this.four);
        this.four.setNext(this.first);

        boolean actual = Node.hasCycle(this.first);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenNodesHasNoCycleThenFalse() {
        this.first.setNext(this.two);
        this.two.setNext(this.third);
        this.third.setNext(this.four);
        this.four.setNext(null);

        boolean actual = Node.hasCycle(this.first);
        boolean expected = false;

        assertThat(actual, is(expected));
    }

}
