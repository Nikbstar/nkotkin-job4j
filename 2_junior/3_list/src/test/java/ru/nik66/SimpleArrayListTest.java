package ru.nik66;

import org.junit.Test;

import java.util.ConcurrentModificationException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayListTest {

    @Test
    public void whenCreateSimpleArrayListThenLengthIsTen() {
        SimpleArrayList<String> myList = new SimpleArrayList<>();
        int actual = myList.getLength();
        int expected = 10;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCreateSimpleArrayListWithZeroLengthAndAddElements() {
        SimpleArrayList<String> myList = new SimpleArrayList<>(0);
        assertThat(myList.getLength(), is(0));
        myList.add("1");
        assertThat(myList.getLength(), is(1));
        myList.add("1");
        assertThat(myList.getLength(), is(2));
        myList.add("1");
        assertThat(myList.getLength(), is(4));
        myList.add("1");
        assertThat(myList.getLength(), is(4));
    }

    @Test
    public void whenGetElement() {
        SimpleArrayList<String> myList = new SimpleArrayList<>();
        myList.add("1");
        String actual = myList.get(0);
        String expected = "1";
        assertThat(actual, is(expected));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenChangeListWhileIteratorThenThrowConcurrentModificationException() {
        SimpleArrayList<String> myList = new SimpleArrayList<>();
        myList.add("1");
        for (String s : myList) {
            myList.add("2");
        }
    }

    @Test
    public void whenListContainsElement() {
        SimpleArrayList<String> myList = new SimpleArrayList<>();
        myList.add("1");
        boolean actual = myList.contains("1");
        boolean expected = true;
        assertThat(actual, is(expected));
        actual = myList.contains("2");
        expected = false;
        assertThat(actual, is(expected));
    }

}
