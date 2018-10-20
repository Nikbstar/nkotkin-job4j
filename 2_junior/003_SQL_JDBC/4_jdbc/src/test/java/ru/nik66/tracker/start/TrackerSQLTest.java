package ru.nik66.tracker.start;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerSQLTest {

    private TrackerSQL tracker;

    @Before
    public void init() {
        this.tracker = new TrackerSQL();
    }

    @Test
    public void whenCheckConnection() {
        //assertThat(this.tracker.init(), is(true));
    }


}