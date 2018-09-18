package ru.nik66.calculate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for Calculate.
 *
 * @author Nikolay Kotkin
 * @version $Id$
 * @since 0.1
 */
public class CalculateTest {

    /**
     * Test echo with normal string.
     */
    @Test
    public void whenSetStopInEchoThenReturnThreeStops() {
        Calculate calc = new Calculate();
        String actual = calc.echo("stop");
        String expected = "stop stop stop";
        assertThat(actual, is(expected));
    }

    /**
     * Test echo with nulls.
     */
    @Test
    public void whenSetNullInEchoThenReturnTwoSpaces() {
        Calculate calc = new Calculate();
        String actual = calc.echo(null);
        String expected = "null null null";
        assertThat(actual, is(expected));
    }

}
