package ru.nik66.tracker.start;

import ru.nik66.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class EditItem extends BaseAction {

    protected EditItem(int key, String name, Consumer<String> media) {
        super(key, name, media);
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        this.media.accept("~~~~~ Edit Item ~~~~~");
        Item item = tracker.findById(input.ask("Enter item id: "));
        if (item == null) {
            this.media.accept("~~~~~ Item not found! ~~~~~");
        } else {
            item.setName(input.ask("Enter item name:"));
            item.setDescription(input.ask("Enter item description: "));
            tracker.replace(item);
        }
    }

}

public class MenuTracker {

    private static final int ADD = 0;
    private static final int ALL = 1;
    public static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int ID = 4;
    private static final int NAME = 5;
    public static final int EXIT = 6;

    private Input input;
    private ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private Consumer<String> media;

    public MenuTracker(Input input, Consumer<String> media, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
        this.media = media;
    }

    public void init() {
        // inner nonstatic class
        this.actions.add(this.new AddItem(ADD, "Add new item.", this.media));
        // inner static class
        this.actions.add(new MenuTracker.ShowItems(ALL, "Show all items.", this.media));
        // outer class
        this.actions.add(new EditItem(EDIT, "Edit items.", this.media));
        // anonymous class
        this.actions.add(new BaseAction(DELETE, "Delete item.", this.media) {
            @Override
            public void execute(Input input, ITracker tracker) {
                this.media.accept("~~~~~ Delete Item ~~~~~");
                if (!tracker.delete(input.ask("Enter item id: "))) {
                    this.media.accept("~~~~~ Item not found! ~~~~~");
                }
            }
        });
        this.actions.add(this.new FindById(ID, "Find item by id.", this.media));
        this.actions.add(this.new FindByName(NAME, "Find items by name.", this.media));
        this.actions.add(this.new Exit(EXIT, "Exit", this.media));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        this.media.accept("~~~~~ MenuTracker ~~~~~");
        for (UserAction action : this.actions) {
            if (action != null) {
                this.media.accept(action.info());
            }
        }
    }

    public List<UserAction> getActions() {
        return this.actions;
    }

    private class AddItem extends BaseAction {

        protected AddItem(int key, String name, Consumer<String> media) {
            super(key, name, media);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            this.media.accept("~~~~~ Add New Item ~~~~~");
            String name = input.ask("Enter item name: ");
            String description = input.ask("Enter item description: ");
            Item item = new Item(name, description, 1L);
            tracker.add(item);
            this.media.accept("~~~~~ New item id: " + item.getId() + " ~~~~~");
        }

    }

    private static class ShowItems extends BaseAction {

        protected ShowItems(int key, String name, Consumer<String> media) {
            super(key, name, media);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            this.media.accept("~~~~~ Show All Items ~~~~~");
            for (Item item : tracker.findAll()) {
                this.media.accept(String.format("id: %s\tname: %s\tdescription: %s\tdate: %s",
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCreate()
                ));
                this.media.accept("=================================================");
            }
        }

    }

    private class FindById extends BaseAction {

        protected FindById(int key, String name, Consumer<String> media) {
            super(key, name, media);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            this.media.accept("~~~~~ Find Item By Id ~~~~~");
            Item item = tracker.findById(input.ask("Enter item id: "));
            if (item == null) {
                this.media.accept("~~~~~ Item not found! ~~~~~");
            } else {
                this.media.accept(String.format("id: %s\tname: %s\tdescription: %s\tdate: %s",
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCreate()
                ));
                this.media.accept("=================================================");
            }
        }

    }

    private class FindByName extends BaseAction {

        protected FindByName(int key, String name, Consumer<String> media) {
            super(key, name, media);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            this.media.accept("~~~~~ Find Item By Name ~~~~~");
            List<Item> items = tracker.findByName(input.ask("Enter item name: "));
            if (items.size() == 0) {
                this.media.accept("~~~~~ Items not found! ~~~~~");
            } else {
                for (Item item : items) {
                    this.media.accept(String.format("id: %s\tname: %s\tdescription: %s\tdate: %s",
                            item.getId(),
                            item.getName(),
                            item.getDescription(),
                            item.getCreate()
                    ));
                    this.media.accept("=================================================");
                }
            }
        }

    }

    private class Exit extends BaseAction {

        protected Exit(int key, String name, Consumer<String> media) {
            super(key, name, media);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
        }

    }

}
