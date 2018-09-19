package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FindLoopTest {

    @Test
    public void whenFindValueOfArrayThenReturnIndexThatOne() throws Exception {
        FindLoop findLoop = new FindLoop();
        int[] arr = {1, 2, 3};

        int actual = findLoop.indexOf(arr, 2);
        int expected = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenNotFindValueThenReturnMinusOne() throws Exception {
        FindLoop findLoop = new FindLoop();
        int[] arr = {1, 2, 3};

        int actual = findLoop.indexOf(arr, 4);
        int expected = -1;

        assertThat(actual, is(expected));
    }

}
