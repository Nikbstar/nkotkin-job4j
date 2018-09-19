package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TurnTest {

    @Test
    public void whenAddOddArrayThenReturnTurnedThatOne() throws Exception {
        Turn turn = new Turn();
        int[] arr = {1, 2, 3, 4, 5};

        int[] actual = turn.back(arr);
        int[] expected = {5, 4, 3, 2, 1};

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAddEvenArrayThenReturnTurnedThatOne() throws Exception {
        Turn turn = new Turn();
        int[] arr = {4, 1, 6, 2};

        int[] actual = turn.back(arr);
        int[] expected = {2, 6, 1, 4};

        assertThat(actual, is(expected));
    }

}
