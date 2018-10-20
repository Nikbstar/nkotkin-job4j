package ru.nik66.magnit;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MagnitTest {

    @Test
    public void sum() {
        Magnit magnit = new Magnit(new File("magnit.xsl"));
        long actual = magnit.sum(3);
        long expected = 6L;
        assertThat(actual, is(expected));
    }
    @Test
    public void bigsum() {
        Magnit magnit = new Magnit(new File("magnit.xsl"));
        long actual = magnit.sum(1000000);
        long expected = 500000500000L;
        assertThat(actual, is(expected));
    }
}