package ru.nik66.calculator;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class FitTest {

    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double actual = fit.manWeight(180);
        double expected = 92d;
        assertThat(actual, closeTo(expected, 0.1));
    }

    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double actual = fit.womanWeight(170);
        double expected = 69d;
        assertThat(actual, closeTo(expected, 0.1));
    }

}