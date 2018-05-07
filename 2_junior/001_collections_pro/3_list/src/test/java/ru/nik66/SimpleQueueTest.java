package ru.nik66;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    @Test
    public void whenPushAndPollThenFIFO() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("1");
        queue.push("2");
        queue.push("3");

        assertThat(queue.poll(), is("1"));
        assertThat(queue.poll(), is("2"));
        assertThat(queue.poll(), is("3"));
    }

}
