package ru.nik66;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void whenAddArrayThenReturnSortArray() throws Exception {
        BubbleSort bs = new BubbleSort();
        int[] arr = {5, 1, 2, 7, 3};

        int[] actual = bs.sort(arr);
        int[] expected = {1, 2, 3, 5, 7};

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAddHardArrayThenSortArray() throws Exception {
        BubbleSort bs = new BubbleSort();
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        int[] actual = bs.sort(arr);
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertThat(actual, is(expected));
    }

}
