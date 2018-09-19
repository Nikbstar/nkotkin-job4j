package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixCheckTest {

    @Test
    public void whenDataMonoByTrueThenTrue() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        boolean actual = check.mono(input);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false},
                {false, false, true},
                {true, false, true}
        };
        boolean actual = check.mono(input);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenOneDiagonalIsMonoTrueAndAnotherIsMonoFalseThenTrue() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, false},
                {false, true}
        };
        boolean actual = check.mono(input);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenOneDiagonalIsMonoAndAnotherIsNotMonoThenFalse() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, false},
                {true, true}
        };
        boolean actual = check.mono(input);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}
