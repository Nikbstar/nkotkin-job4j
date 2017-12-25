package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaintTest {

    @Test
    public void whenPyramid2LevelsThen() {
        Paint paint = new Paint();
        String eol = System.lineSeparator();

        String actual = paint.pyramid(2);
        String expected = String.format(" ^ %s^^^%s", eol, eol);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenPyramid3LevelsThen() {
        Paint paint = new Paint();
        String eol = System.lineSeparator();

        String actual = paint.pyramid(3);
        String expected = String.format("  ^  %s ^^^ %s^^^^^%s", eol, eol, eol);

        assertThat(actual, is(expected));
    }

}
