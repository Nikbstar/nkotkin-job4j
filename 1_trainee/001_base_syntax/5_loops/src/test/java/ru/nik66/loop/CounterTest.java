package ru.nik66.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CounterTest {

    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();

        int actual = counter.add(1, 10);
        int expected = 30;

        assertThat(actual, is(expected));
    }


}
