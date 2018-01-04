package ru.nik66;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PaintTest {

    private final String eol = System.lineSeparator();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();


    @Before
    public void init() throws Exception {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void stdout() throws Exception {
        System.setOut(System.out);
    }

    @Test
    public void whenDrawSquare() throws Exception {
        new Paint().draw(new Square());

        String actual = new String(this.out.toByteArray());
        String expected = new StringBuilder()
                .append("++++").append(this.eol)
                .append("+  +").append(this.eol)
                .append("+  +").append(this.eol)
                .append("++++").append(this.eol)
                .append(this.eol)
                .toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenDrawTriangle() throws Exception {
        new Paint().draw(new Triangle());

        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("   +").append(this.eol)
                .append("  + +").append(this.eol)
                .append(" +   +").append(this.eol)
                .append("+++++++").append(this.eol)
                .append(this.eol)
                .toString();

        assertThat(actual, is(expected));
    }

}
