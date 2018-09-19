package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArrayCharTest {

    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean actual = word.startWith("He");
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean actual = word.startWith("Hi");
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}
