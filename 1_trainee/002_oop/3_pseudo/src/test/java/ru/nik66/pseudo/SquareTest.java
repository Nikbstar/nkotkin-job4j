package ru.nik66.pseudo;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SquareTest {

    @Test
    public void whenDrawSquareThenItOneDrawsInConsole() throws Exception {
        Square square = new Square();
        String eol = System.lineSeparator();

        String actual = square.draw();
        String expected = new StringBuilder()
                .append("++++").append(eol)
                .append("+  +").append(eol)
                .append("+  +").append(eol)
                .append("++++").append(eol)
                .toString();

        assertThat(actual, is(expected));
    }

}
