package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConvertListTest {

    ConvertList convert;
    List<Integer> list;

    @Before
    public void initialize() {
        convert = new ConvertList();
        list = new ArrayList<>(7);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
    }

    @Test
    public void whenConvertToList() {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7}};

        List<Integer> actual = convert.toList(array);
        List<Integer> expected = list;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenConvertToArray() {
        int[][] actual = convert.toArray(list, 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};

        assertThat(actual, is(expected));
    }

    @Test
    public void whenConvertListOfArraysToList() {
        List<int[]> ints = new ArrayList<>();
        ints.add(new int[]{1, 2});
        ints.add(new int[]{3, 4, 5, 6});
        List<Integer> actual = convert.convert(ints);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(actual, is(expected));
    }

}
