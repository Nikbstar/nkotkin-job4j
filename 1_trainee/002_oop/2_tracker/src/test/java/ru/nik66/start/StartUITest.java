package ru.nik66.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.nik66.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, this.tracker).init();

        String actual = this.tracker.findAll()[1].getName();
        String expected = "test name";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserEditItemThenItemEdited() throws Exception {
        Input input = new StubInput(new String[]{"2", this.first.getId(), "edited name", "desc", "6"});
        new StartUI(input, this.tracker).init();

        String actual = this.tracker.findAll()[0].getName();
        String expected = "edited name";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserDeleteItemThenItOneDeleted() throws Exception {
        Item second = new Item("second", "desc", 1L);
        this.tracker.add(second);
        Input input = new StubInput(new String[]{"3", this.first.getId(), "6"});
        new StartUI(input, this.tracker).init();

        String actual = this.tracker.findAll()[0].getName();
        String expected = "second";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserSelectShowAllItemsThenConsolePrintItemsList() throws Exception {
        Input input = new StubInput(new String[] {"1", "6"});
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .append("~~~~~ Show All Items ~~~~~").append(this.EOL)
                .append("id: ").append(this.first.getId()).append('\t')
                .append("name: ").append(this.first.getName()).append('\t')
                .append("description: ").append(this.first.getDescription()).append('\t')
                .append("date: ").append(this.first.getCreate()).append(this.EOL)
                .append("=================================================").append(this.EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserFindItemByIdThenConsolePrintThisItem() throws Exception {
        Input input = new StubInput(new String[]{"4", this.first.getId(), "6"});
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .append("~~~~~ Find Item By Id ~~~~~").append(this.EOL)
                .append("id: ").append(this.first.getId()).append('\t')
                .append("name: ").append(this.first.getName()).append('\t')
                .append("description: ").append(this.first.getDescription()).append('\t')
                .append("date: ").append(this.first.getCreate()).append(this.EOL)
                .append("=================================================").append(this.EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserInputWrongIdThenConsolePrintThatItemNotFound() throws Exception {
        Input input = new StubInput(new String[]{"4", "1", "6"});
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .append("~~~~~ Find Item By Id ~~~~~").append(this.EOL)
                .append("~~~~~ Item not found! ~~~~~").append(this.EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUserInputPartOfNameThenConsolePrintItemsListWithSimilarNames() throws Exception {
        Input input = new StubInput(new String[]{"5", "fir", "6"});
        new StartUI(input, this.tracker).init();
        String actual = this.out.toString();
        String expected = new StringBuilder()
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .append("~~~~~ Find Item By Name ~~~~~").append(this.EOL)
                .append("id: ").append(this.first.getId()).append('\t')
                .append("name: ").append(this.first.getName()).append('\t')
                .append("description: ").append(this.first.getDescription()).append('\t')
                .append("date: ").append(this.first.getCreate()).append(this.EOL)
                .append("=================================================").append(this.EOL)
                .append("~~~~~ MenuTracker ~~~~~").append(this.EOL)
                .append("0. Add new item.").append(this.EOL)
                .append("1. Show all items.").append(this.EOL)
                .append("2. Edit items.").append(this.EOL)
                .append("3. Delete item.").append(this.EOL)
                .append("4. Find item by id.").append(this.EOL)
                .append("5. Find items by name.").append(this.EOL)
                .append("6. Exit").append(this.EOL)
                .toString();
        assertThat(actual, is(expected));
    }

}
