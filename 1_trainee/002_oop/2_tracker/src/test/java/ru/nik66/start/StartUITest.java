package ru.nik66.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.nik66.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {

    private Tracker tracker;
    private Item first;
    private static final String EOL = System.lineSeparator();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void init() throws Exception {
        System.setOut(new PrintStream(this.out));
        this.tracker = new Tracker();
        this.first = new Item("first", "desc", 1L);
        this.tracker.add(this.first);
    }

    @After
    public void stdout() throws Exception {
        System.setOut(System.out);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() throws Exception {
        List<String> answers = Arrays.asList("0", "test name", "desc", "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();

        String actual = this.tracker.findAll().get(1).getName();
        String expected = "test name";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserEditItemThenItemEdited() throws Exception {
        List<String> answers = Arrays.asList("2", this.first.getId(), "edited name", "desc", "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();

        String actual = this.tracker.findAll().get(0).getName();
        String expected = "edited name";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserDeleteItemThenItOneDeleted() throws Exception {
        Item second = new Item("second", "desc", 1L);
        this.tracker.add(second);
        List<String> answers = Arrays.asList("3", this.first.getId(), "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();

        String actual = this.tracker.findAll().get(0).getName();
        String expected = "second";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserSelectShowAllItemsThenConsolePrintItemsList() throws Exception {
        List<String> answers = Arrays.asList("1", "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .append("~~~~~ Show All Items ~~~~~").append(EOL)
                .append("id: ").append(this.first.getId()).append('\t')
                .append("name: ").append(this.first.getName()).append('\t')
                .append("description: ").append(this.first.getDescription()).append('\t')
                .append("date: ").append(this.first.getCreate()).append(EOL)
                .append("=================================================").append(EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserFindItemByIdThenConsolePrintThisItem() throws Exception {
        List<String> answers = Arrays.asList("4", this.first.getId(), "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .append("~~~~~ Find Item By Id ~~~~~").append(EOL)
                .append("id: ").append(this.first.getId()).append('\t')
                .append("name: ").append(this.first.getName()).append('\t')
                .append("description: ").append(this.first.getDescription()).append('\t')
                .append("date: ").append(this.first.getCreate()).append(EOL)
                .append("=================================================").append(EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserInputWrongIdThenConsolePrintThatItemNotFound() throws Exception {
        List<String> answers = Arrays.asList("4", "1", "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .append("~~~~~ Find Item By Id ~~~~~").append(EOL)
                .append("~~~~~ Item not found! ~~~~~").append(EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserInputPartOfNameThenConsolePrintItemsListWithSimilarNames() throws Exception {
        List<String> answers = Arrays.asList("5", "fir", "6");
        Input input = new StubInput(answers);
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .append("~~~~~ Find Item By Name ~~~~~").append(EOL)
                .append("id: ").append(this.first.getId()).append('\t')
                .append("name: ").append(this.first.getName()).append('\t')
                .append("description: ").append(this.first.getDescription()).append('\t')
                .append("date: ").append(this.first.getCreate()).append(EOL)
                .append("=================================================").append(EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(EOL)
                .append("0. Add new item.").append(EOL)
                .append("1. Show all items.").append(EOL)
                .append("2. Edit items.").append(EOL)
                .append("3. Delete item.").append(EOL)
                .append("4. Find item by id.").append(EOL)
                .append("5. Find items by name.").append(EOL)
                .append("6. Exit").append(EOL)
                .toString();
        assertThat(actual, is(expected));
    }

}
