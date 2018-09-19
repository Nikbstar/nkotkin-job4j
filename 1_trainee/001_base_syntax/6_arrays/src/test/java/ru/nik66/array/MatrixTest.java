package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixTest {

    @Test
    public void whenAddMatrixThenReturnMultipleTable() throws Exception {
        Matrix matrix = new Matrix();

        int[][] actual = matrix.multiple(2);
        int[][] expected = {{1, 2}, {2, 4}};

        assertThat(actual, is(expected));
    }

}
