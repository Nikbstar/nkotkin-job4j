package ru.nik66.start;

import org.junit.Test;
import ru.nik66.models.Item;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() throws Exception {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();

        String actual = tracker.findAll()[0].getName();
        String expected = "test name";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserEditItemThenItemEdited() throws Exception {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc", 1L);
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "edited name", "desc", "6"});
        new StartUI(input, tracker).init();

        String actual = tracker.findAll()[0].getName();
        String expected = "edited name";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserDeleteItemThenItOneDeleted() throws Exception {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "desc", 1L);
        Item second = new Item("second", "desc", 1L);
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        new StartUI(input, tracker).init();

        String actual = tracker.findAll()[0].getName();
        String expected = "second";

        assertThat(actual, is(expected));
    }

}
