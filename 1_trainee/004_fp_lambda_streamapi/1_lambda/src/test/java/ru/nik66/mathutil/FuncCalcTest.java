package ru.nik66.mathutil;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FuncCalcTest {

    FuncCalc calc;

    @Before
    public void init() {
        this.calc = new FuncCalc();
    }

    @Test
    public void whenLineFrom1To3Then123() {
        List<Double> actual = this.calc.line(1, 3);
        List<Double> expected = Arrays.asList(1d, 2d, 3d);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenQuadraticFrom1To3Then146() {
        List<Double> actual = this.calc.quadratic(1, 3);
        List<Double> expected = Arrays.asList(1d, 4d, 9d);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenLogFrom1To3ThenLog123() {
        List<Double> actual = this.calc.log(1, 3);
        List<Double> expected = Arrays.asList(Math.log(1d), Math.log(2d), Math.log(3d));
        assertThat(actual, is(expected));
    }

}
