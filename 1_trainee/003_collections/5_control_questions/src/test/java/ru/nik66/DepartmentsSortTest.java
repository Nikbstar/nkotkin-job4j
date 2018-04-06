package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DepartmentsSortTest {

    DepartmentsSort sort;

    @Before
    public void initialize() {
        this.sort = new DepartmentsSort(new String[] {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
                "K3"
        });
    }

    @Test
    public void whenSort() {
        String[] actual = this.sort.sort();
        String[] expected = new String[] {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
                "K3"
        };
        assertThat(actual, is(expected));
    }

    @Test
    public void whenReverseSort() {
        String[] actual = this.sort.reverse();
        String[] expected = new String[] {
                "K3",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        };
        assertThat(actual, is(expected));
    }

}
