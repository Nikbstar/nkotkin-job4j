package ru.nik66;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    @Test
    public void whenInsert() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        assertThat(map.size(), is(0));
        boolean actual = map.insert("1", "a");
        boolean expected = true;
        assertThat(actual, is(expected));
        assertThat(map.size(), is(1));
        map.insert("1", "b");
        assertThat(map.size(), is(1));
        map.insert("2", "c");
        assertThat(map.size(), is(2));
    }

    @Test
    public void whenSizeMoreThanThresholdThenIncreaseArrayLength() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>(3);
        map.insert("1", "a");
        assertThat(map.getHashTableLength(), is(3));
        assertThat(map.getThreshold(), is(2.25f));
        map.insert("2", "b");
        assertThat(map.getHashTableLength(), is(3));
        assertThat(map.getThreshold(), is(2.25f));
        map.insert("3", "c");
        assertThat(map.getHashTableLength(), is(6));
        assertThat(map.getThreshold(), is(4.5f));
    }

    @Test
    public void whenContainsKey() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("1", "a");
        assertThat(map.containsKey("1"), is(true));
        assertThat(map.containsKey("2"), is(false));
    }

    @Test
    public void whenGetValueByKey() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("1", "a");
        assertThat(map.get("1"), is("a"));
        assertThat(map.get("2"), is((String) null));
        map.insert("1", "b");
        assertThat(map.get("1"), is("b"));
    }

    @Test
    public void whenDeleteByKey() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("1", "a");
        assertThat(map.get("1"), is("a"));
        boolean actual = map.delete("1");
        boolean expected = true;
        assertThat(actual, is(expected));
        assertThat(map.containsKey("1"), is(false));
        assertThat(map.size(), is(0));
        assertThat(map.delete("2"), is(false));
    }

    @Test
    public void whenIterator() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("1", "a");
        Iterator<String> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("a"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElementException() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        Iterator<String> iterator = map.iterator();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentModificationException() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("1", "a");
        map.insert("2", "b");
        for (String s : map) {
            map.insert("3", "c");
        }
    }

}
