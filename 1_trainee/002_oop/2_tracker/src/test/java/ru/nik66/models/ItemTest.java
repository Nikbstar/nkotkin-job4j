package ru.nik66.models;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {

    @Test
    public void whenCreateTaskAndGetNameThenReturnTaskName() {
        Task task = new Task("TaskName", "", 1L);

        String actual = task.getName();
        String expected = "TaskName";

        assertThat(actual, is(expected));
    }

}
