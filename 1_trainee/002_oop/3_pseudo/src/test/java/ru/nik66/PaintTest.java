package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PaintTest {

    @Test
    public void whenDrawSquare() throws Exception {
        String eol = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());

        String actual = new String(out.toByteArray());
        String expected = new StringBuilder()
                .append("++++").append(eol)
                .append("+  +").append(eol)
                .append("+  +").append(eol)
                .append("++++").append(eol)
                .append(eol)
                .toString();
        assertThat(actual, is(expected));
        System.setOut(System.out);
    }

    @Test
    public void whenDrawTriangle() throws Exception {
        String eol = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());

        String actual = out.toString();
        String expected = new StringBuilder()
                .append("   +").append(eol)
                .append("  + +").append(eol)
                .append(" +   +").append(eol)
                .append("+++++++").append(eol)
                .append(eol)
                .toString();
        assertThat(actual, is(expected));
        System.setOut(System.out);
    }

}
