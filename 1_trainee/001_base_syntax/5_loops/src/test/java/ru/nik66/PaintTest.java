package ru.nik66;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaintTest {

    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String eol = System.lineSeparator();

        String actual = paint.rightTrl(4);
        System.out.println(actual);
        String expected = new StringJoiner(eol, "", eol)
                .add("^   ")
                .add("^^  ")
                .add("^^^ ")
                .add("^^^^")
                .toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String eol = System.lineSeparator();

        String actual = paint.leftTrl(4);
        System.out.println(actual);
        String expected = new StringJoiner(eol, "", eol)
                .add("   ^")
                .add("  ^^")
                .add(" ^^^")
                .add("^^^^")
                .toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenPyramid2LevelsThen() {
        Paint paint = new Paint();
        String eol = System.lineSeparator();

        String actual = paint.pyramid(2);
        System.out.println(actual);
        String expected = String.format(" ^ %s^^^%s", eol, eol);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenPyramid3LevelsThen() {
        Paint paint = new Paint();

        String actual = paint.pyramid(3);
        System.out.println(actual);
        String expected = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("  ^  ")
                .add(" ^^^ ")
                .add("^^^^^")
                .toString();

        assertThat(actual, is(expected));
    }

}
