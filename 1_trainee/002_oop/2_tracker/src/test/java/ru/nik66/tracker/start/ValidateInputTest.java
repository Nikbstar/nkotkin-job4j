package ru.nik66.tracker.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

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
        List<String> answers = Arrays.asList("a", "1");
        ValidateInput input = new ValidateInput(new StubInput(answers));
        input.ask("", Arrays.asList(0));
        assertThat(this.mem.toString(), is(String.format("Please enter validate data again.%n")));
    }

}
