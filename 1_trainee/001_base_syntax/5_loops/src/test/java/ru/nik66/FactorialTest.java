package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FactorialTest {

    @Test
    public void whenSetFiveThenReturnOneHundredTwenty() {
        Factorial factorial = new Factorial();

        int actual = factorial.calc(5);
        int expected = 120;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSetZeroThenReturnOne() {
        Factorial factorial = new Factorial();

        int actual = factorial.calc(0);
        int expected = 1;

        assertThat(actual, is(expected));
    }

}
