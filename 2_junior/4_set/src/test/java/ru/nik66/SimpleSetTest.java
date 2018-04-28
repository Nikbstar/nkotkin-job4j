package ru.nik66;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void whenAddDuplicateElementThenItsIgnores() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("1");

        assertThat(set.size(), is(1));
    }

    @Test
    public void whenIterator() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("2");
        set.add("2");
        set.add("1");
        set.add("2");
        Iterator<String> iterator = set.iterator();

        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("2"));

        assertThat(iterator.hasNext(), is(false));
    }

}
