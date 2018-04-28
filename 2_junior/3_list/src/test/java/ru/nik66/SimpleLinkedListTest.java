package ru.nik66;

import org.junit.Test;

import java.util.ConcurrentModificationException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleLinkedListTest {

    @Test
    public void whenAddElementsThenSizeIncreases() {
        SimpleLinkedList<String> myList = new SimpleLinkedList<>();
        assertThat(myList.size(), is(0));
        myList.add("1");
        assertThat(myList.size(), is(1));
        myList.add("2");
        assertThat(myList.size(), is(2));
        myList.add("3");
        assertThat(myList.size(), is(3));
    }

    @Test
    public void whenAddElementsThenGetThemByIndex() {
        SimpleLinkedList<String> myList = new SimpleLinkedList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        assertThat(myList.get(0), is("1"));
        assertThat(myList.get(1), is("2"));
        assertThat(myList.get(2), is("3"));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetWrongIndexThenThrowIndexOutOfBoundException() {
        SimpleLinkedList<String> myList = new SimpleLinkedList<>();
        myList.get(3);
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenChangeLinkedListWhileIteratorThenThrowConcurrentModificationException() {
        SimpleLinkedList<String> myList = new SimpleLinkedList<>();
        myList.add("1");
        for (String s : myList) {
            myList.add("2");
        }
    }

    @Test
    public void whenContains() {
        SimpleLinkedList<String> myList = new SimpleLinkedList<>();
        myList.add("1");
        boolean actual = myList.contains("1");
        boolean expected = true;
        assertThat(actual, is(expected));
        actual = myList.contains("2");
        expected = false;
        assertThat(actual, is(expected));
    }

}
