package ru.nik66;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
public class CalculateTest {

	@Test
	public void whenSetStopInEchoThenReturnThreeStops() {
		Calculate calc = new Calculate();
		String actual = calc.echo("stop");
		String expected = "stop stop stop";
		assertThat(actual, is(expected));
	}

  @Test
  public void whenSetNullInEchoThenReturnTwoSpaces() {
    Calculate calc = new Calculate();
    String actual = calc.echo(null);
    String expected = "null null null";
    assertThat(actual, is(expected));
  }

}

