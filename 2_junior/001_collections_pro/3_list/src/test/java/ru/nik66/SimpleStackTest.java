package ru.nik66;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test
    public void whenPushAndPollThenLIFO() {
        SimpleStack<String> queue = new SimpleStack<>();
        queue.push("1");
        queue.push("2");
        queue.push("3");

        assertThat(queue.poll(), is("3"));
        assertThat(queue.poll(), is("2"));
        assertThat(queue.poll(), is("1"));
    }

}
