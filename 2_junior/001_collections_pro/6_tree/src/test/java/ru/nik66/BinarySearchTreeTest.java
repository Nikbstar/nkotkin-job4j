package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTreeTest {

    @Test
    public void whenAddAndIteratorThen() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(14);
        bst.add(1);
        bst.add(6);
        bst.add(7);
        bst.add(13);
        bst.add(4);
        Iterator<Integer> iterator = bst.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(10));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(13));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(14));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElementException() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(8);
        Iterator<Integer> iterator = bst.iterator();
        iterator.next();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentModificationException() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(8);
        bst.add(3);
        bst.add(10);
        for (Integer integer : bst) {
            bst.add(1);
        }
    }

}
