package ru.nik66.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ValidateInputTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(System.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"a", "1"}));
        input.ask("", new int[] {0});
        assertThat(this.mem.toString(), is(String.format("Please enter validate data again.%n")));
    }

}
