package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    SimpleArray<String> sa;

    @Before
    public void initialize() {
        this.sa = new SimpleArray<>(5);
        this.sa.add("a");
    }

    @Test
    public void whenAddAndGetElement() {
        String actual = this.sa.get(0);
        String expected = "a";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteElement() {
        this.sa.delete(0);
        String actual = this.sa.get(0);
        String expected = null;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSetElement() {
        this.sa.set(0, "b");
        String actual = this.sa.get(0);
        String expected = "b";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenIterator() {
        this.sa.add("b");
        this.sa.add("c");
        this.sa.add("d");
        this.sa.add("e");
        String actual = "";
        for (String s : sa) {
            actual += s;
        }
        String expected = "abcde";
        assertThat(actual, is(expected));
    }

}
