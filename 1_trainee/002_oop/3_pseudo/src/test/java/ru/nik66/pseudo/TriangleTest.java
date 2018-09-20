package ru.nik66.pseudo;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TriangleTest {

    @Test
    public void whenDrawTriangleThenItOneDrawsInConsole() throws Exception {
        Triangle triangle = new Triangle();
        String eol = System.lineSeparator();

        String actual = triangle.draw();
        String expected = new StringBuilder()
                .append("   +").append(eol)
                .append("  + +").append(eol)
                .append(" +   +").append(eol)
                .append("+++++++").append(eol)
                .toString();

        assertThat(actual, is(expected));
    }

}
