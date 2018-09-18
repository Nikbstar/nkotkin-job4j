package ru.nik66.converter;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConverterTest {

    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int actual = converter.rubleToDollar(60);
        int expected = 1;
        assertThat(actual, is(expected));
    }

    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int actual = converter.rubleToEuro(70);
        int expected = 1;
        assertThat(actual, is(expected));
    }

}
