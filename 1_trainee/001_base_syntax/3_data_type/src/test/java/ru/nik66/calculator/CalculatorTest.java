package ru.nik66.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOneAndOneThenReturnTwo() throws Exception {
        Calculator calculator = new Calculator();
        calculator.add(1d, 1d);
        double actual = calculator.getResult();
        double expected = 2d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSubtractOneAndOneThenReturnZero() throws Exception {
        Calculator calculator = new Calculator();
        calculator.subtract(1d, 1d);
        double actual = calculator.getResult();
        double expected = 0d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDivTwoAndThwThenReturnOne() throws Exception {
        Calculator calculator = new Calculator();
        calculator.div(2d, 2d);
        double actual = calculator.getResult();
        double expected = 1d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMultipleTwoAndTwoThenReturnFour() throws Exception {
        Calculator calculator = new Calculator();
        calculator.multiple(2d, 2d);
        double actual = calculator.getResult();
        double expected = 4d;
        assertThat(actual, is(expected));
    }

}
