package ru.nik66.tracker.start;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.nik66.tracker.models.Bug;
import ru.nik66.tracker.models.Item;
import ru.nik66.tracker.models.Task;

import java.util.Arrays;
import java.util.List;

public class TrackerTest {

    @Test
    public void whenGetItemByIdThenReturnThatOne() throws Exception {
        Tracker tracker = new Tracker();
        Task task = new Task("TaskName", "TaskDescription", 1L);
        tracker.add(task);
        Item actual = tracker.findById(task.getId());
        Item expected = task;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindAllItemsThenReturnArrayWithoutNulls() throws Exception {
        Tracker tracker = new Tracker();
        Task task = new Task("name", "description", 1L);
        tracker.add(task);
        List<Item> actual = tracker.findAll();
        List<Item> expected = Arrays.asList(task);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUpdateItemThenItUpdatesInArray() throws Exception {
        Tracker tracker = new Tracker();
        Task task = new Task("name", "description", 1L);
        tracker.add(task);
        Bug bug = new Bug("bug", "", 1L);
        bug.setId(task.getId());
        tracker.replace(bug);
        String actual = tracker.findById(task.getId()).getName();
        String expected = "bug";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteItemThenItDeleted() throws Exception {
        Tracker tracker = new Tracker();
        Task task = new Task("task", "", 1L);
        Bug bug = new Bug("bug", "", 1L);
        tracker.add(task);
        tracker.add(bug);
        tracker.delete(task.getId());
        List<Item> actual = tracker.findAll();
        List<Item> expected = Arrays.asList(bug);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteLastItemThenItDeleted() throws Exception {
        Tracker tracker = new Tracker();
        Task task = new Task("tt", "", 1L);
        Bug bug = new Bug("bb", "", 1L);
        tracker.add(task);
        tracker.add(bug);
        tracker.delete(bug.getId());
        List<Item> actual = tracker.findAll();
        List<Item> expected = Arrays.asList(task);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindByNameThenReturnArrayItemsWithThatName() throws Exception {
        Tracker tracker = new Tracker();
        Task task = new Task("tt", "", 1L);
        Task task1 = new Task("tt1", "", 1L);
        Task task2 = new Task("ss", "", 1L);
        tracker.add(task);
        tracker.add(task1);
        tracker.add(task2);
        List<Item> actual = tracker.findByName("tt");
        List<Item> expected = Arrays.asList(task, task1);

        assertThat(actual, is(expected));
    }

}
