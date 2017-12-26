package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SquareTest {

    @Test
    public void whenBoundIsTwoThenReturnArrayWithTwoPowElements() throws Exception {
        Square square = new Square();

        int[] actual = square.calculate(2);
        int[] expected = {1, 4};

        assertThat(actual, is(expected));
    }

}
