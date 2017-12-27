package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SubStringTest {

    @Test
    public void whenFindSubInOriginThenReturnTrue() throws Exception {
        SubString subString = new SubString();
        boolean actual = subString.contains("Приве", "иве");
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenOriginEqualsSubThenReturnTrue() throws Exception {
        SubString subString = new SubString();
        boolean actual = subString.contains("Hello", "Hello");
        boolean expected = true;

        assertThat(actual, is(expected));
    }

}
