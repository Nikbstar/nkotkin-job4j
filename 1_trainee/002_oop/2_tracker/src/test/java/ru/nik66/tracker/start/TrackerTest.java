package ru.nik66.tracker.start;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.nik66.tracker.models.Bug;
import ru.nik66.tracker.models.Item;
import ru.nik66.tracker.models.Task;

import java.util.Arrays;
import java.util.List;

public class TrackerTest {

    private Tracker tracker;

    @Before
    public void init() {
        this.tracker = new Tracker();
    }

    @Test
    public void whenGetItemByIdThenReturnThatOne() throws Exception {
        Task task = new Task("TaskName", "TaskDescription", 1L);
        this.tracker.add(task);
        Item actual = this.tracker.findById(task.getId());
        Item expected = task;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenGetItemByWrongIdThenReturnNull() throws Exception {
        Item actual = this.tracker.findById("123");
        Item expected = null;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindAllItemsThenReturnArrayWithoutNulls() throws Exception {
        Task task = new Task("name", "description", 1L);
        this.tracker.add(task);
        List<Item> actual = this.tracker.findAll();
        List<Item> expected = Arrays.asList(task);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUpdateItemThenItUpdatesInArray() throws Exception {
        Task task = new Task("name", "description", 1L);
        this.tracker.add(task);
        Bug bug = new Bug("bug", "", 1L);
        bug.setId(task.getId());
        this.tracker.replace(bug);
        String actual = this.tracker.findById(task.getId()).getName();
        String expected = "bug";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteItemThenItDeleted() throws Exception {
        Task task = new Task("task", "", 1L);
        Bug bug = new Bug("bug", "", 1L);
        this.tracker.add(task);
        this.tracker.add(bug);
        this.tracker.delete(task.getId());
        List<Item> actual = this.tracker.findAll();
        List<Item> expected = Arrays.asList(bug);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteLastItemThenItDeleted() throws Exception {
        Task task = new Task("tt", "", 1L);
        Bug bug = new Bug("bb", "", 1L);
        this.tracker.add(task);
        this.tracker.add(bug);
        this.tracker.delete(bug.getId());
        List<Item> actual = this.tracker.findAll();
        List<Item> expected = Arrays.asList(task);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindByNameThenReturnArrayItemsWithThatName() throws Exception {
        Task task = new Task("tt", "", 1L);
        Task task1 = new Task("tt1", "", 1L);
        Task task2 = new Task("ss", "", 1L);
        this.tracker.add(task);
        this.tracker.add(task1);
        this.tracker.add(task2);
        List<Item> actual = this.tracker.findByName("tt");
        List<Item> expected = Arrays.asList(task, task1);
        assertThat(actual, is(expected));
    }

}
