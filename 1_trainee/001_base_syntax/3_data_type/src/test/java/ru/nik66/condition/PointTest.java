package ru.nik66.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PointTest {

    @Test
    public void whenThen() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double actual = a.distanceTo(b);
        double expected = 2d;
        assertThat(actual, is(expected));
    }

}
