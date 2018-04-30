package ru.nik66;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleHashSetTest {

    @Test
    public void whenAddElementThenSizeIsChange() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        set.add("1");
        assertThat(set.size(), is(1));
        set.add("2");
        assertThat(set.size(), is(2));
        set.add("2");
        assertThat(set.size(), is(2));
    }

    @Test
    public void whenSizeMoreThanThresholdThenIncreaseArrayLength() {
        SimpleHashSet<String> set = new SimpleHashSet<>(3);
        set.add("1");
        assertThat(set.getHashTableLength(), is(3));
        assertThat(set.getThreshold(), is(2.25f));
        set.add("2");
        assertThat(set.getHashTableLength(), is(3));
        assertThat(set.getThreshold(), is(2.25f));
        set.add("3");
        assertThat(set.getHashTableLength(), is(6));
        assertThat(set.getThreshold(), is(4.5f));
    }

    @Test
    public void whenContainsElement() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        set.add("1");
        assertThat(set.contains("1"), is(true));
        assertThat(set.contains("2"), is(false));
    }

    @Test
    public void whenRemoveElement() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        set.add("1");
        set.add("2");
        assertThat(set.remove("1"), is(true));
        assertThat(set.contains("1"), is(false));
        assertThat(set.size(), is(1));
        assertThat(set.remove("1"), is(false));
    }

    @Test
    public void whenIterator() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        set.add("1");
        Iterator<String> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElementException() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        Iterator<String> iterator = set.iterator();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentModificationException() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        set.add("1");
        for (String s : set) {
            set.add("2");
        }
    }

}
