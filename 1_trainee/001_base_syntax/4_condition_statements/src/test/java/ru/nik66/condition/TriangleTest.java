package ru.nik66.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class TriangleTest {

    @Test
    public void whenThen() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);

        double actual = triangle.period(a.distanceTo(b), b.distanceTo(c), c.distanceTo(a));
        double expected = 3.414213;

        assertThat(actual, closeTo(expected, 0.00001));
    }

    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);

        double actual = triangle.area();
        double expected = 2d;

        assertThat(actual, closeTo(expected, 0.0001));
    }

    @Test
    public void whenIsNoTriangleThenReturnMinusOne() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(0, 0);
        Triangle triangle = new Triangle(a, b, c);

        double actual = triangle.area();
        double expected = -1d;

        assertThat(actual, closeTo(expected, 0.0001));
    }

}
