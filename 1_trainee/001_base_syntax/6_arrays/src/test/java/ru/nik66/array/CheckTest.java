package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CheckTest {

    @Test
    public void whenDataMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true};
        boolean actual = check.mono(input);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDateMonoByFalseThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {false, false, false};
        boolean actual = check.mono(input);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenOneElementIsNotTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean actual = check.mono(input);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}
