package ru.nik66.max;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxTest {

    @Test
    public void whenFirstIsGreaterThanSecondThenReturnFirst() throws Exception {
        Max max = new Max();

        int actual = max.max(1, 0);
        int expected = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSecondIsGreaterThanSecondThenReturnSecond() throws Exception {
        Max max = new Max();

        int actual = max.max(0, 1);
        int expected = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenBothAreEqualThenReturnFirst() throws Exception {
        Max max = new Max();

        int actual = max.max(0, 0);
        int expected = 0;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenFirstOfThreeIsMaxThenReturnThatOne() throws Exception {
        Max max = new Max();

        int actual = max.max(1, 0, 0);
        int expected = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSecondOfThreeIsMaxThenReturnThatOne() throws Exception {
        Max max = new Max();

        int actual = max.max(0, 1, 0);
        int expected = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenThirdOfThreeIsMaxThenReturnThatOne() throws Exception {
        Max max = new Max();

        int actual = max.max(0, 0, 1);
        int expected = 1;

        assertThat(actual, is(expected));
    }
}
