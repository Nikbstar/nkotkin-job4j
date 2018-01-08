package ru.nik66.start;

import ru.nik66.models.Item;

class EditItem implements UserAction {

    @Override
    public int key() {
        return MenuTracker.EDIT;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("~~~~~ Edit Item ~~~~~");
        Item item = tracker.findById(input.ask("Enter item id: "));
        if (item == null) {
            System.out.println("~~~~~ Item not found! ~~~~~");
        } else {
            item.setName(input.ask("Enter item name:"));
            item.setDescription(input.ask("Enter item description: "));
            tracker.replace(item);
        }
    }

    @Override
    public String info() {
        return String.format("%d. %s", this.key(), "Edit items.");
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
    private static final int ACTIONS = 7;

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[ACTIONS];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        // inner nonstatic class
        this.actions[ADD] = this.new AddItem();
        // inner static class
        this.actions[ALL] = new MenuTracker.ShowItems();
        // outer class
        this.actions[EDIT] = new EditItem();
        this.actions[DELETE] = this.new DeleteItem();
        this.actions[ID] = this.new FindById();
        this.actions[NAME] = this.new FindByName();
        this.actions[EXIT] = this.new Exit();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        System.out.println("~~~~~ MenuTracker ~~~~~");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public UserAction[] getActions() {
        return this.actions;
    }

    private class AddItem implements UserAction {

        @Override
        public int key() {
            return ADD;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("~~~~~ Add New Item ~~~~~");
            String name = input.ask("Enter item name: ");
            String description = input.ask("Enter item description: ");
            Item item = new Item(name, description, 1L);
            tracker.add(item);
            System.out.println("~~~~~ New item id: " + item.getId() + " ~~~~~");
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Add new item.");
        }

    }

    private static class ShowItems implements UserAction {

        @Override
        public int key() {
            return ALL;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("~~~~~ Show All Items ~~~~~");
            for (Item item : tracker.findAll()) {
                System.out.printf("id: %s\tname: %s\tdescription: %s\tdate: %s%s",
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCreate(),
                        System.lineSeparator());
                System.out.println("=================================================");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Show all items.");
        }

    }

    private class DeleteItem implements UserAction {

        @Override
        public int key() {
            return DELETE;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("~~~~~ Delete Item ~~~~~");
            if (!tracker.delete(input.ask("Enter item id: "))) {
                System.out.println("~~~~~ Item not found! ~~~~~");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Delete item.");
        }

    }

    private class FindById implements UserAction {

        @Override
        public int key() {
            return ID;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("~~~~~ Find Item By Id ~~~~~");
            Item item = tracker.findById(input.ask("Enter item id: "));
            if (item == null) {
                System.out.println("~~~~~ Item not found! ~~~~~");
            } else {
                System.out.printf("id: %s\tname: %s\tdescription: %s\tdate: %s%s",
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCreate(),
                        System.lineSeparator());
                System.out.println("=================================================");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Find item by id.");
        }

    }

    private class FindByName implements UserAction {

        @Override
        public int key() {
            return NAME;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("~~~~~ Find Item By Name ~~~~~");
            Item[] items = tracker.findByName(input.ask("Enter item name: "));
            if (items == null) {
                System.out.println("~~~~~ Items not found! ~~~~~");
            } else {
                for (Item item : items) {
                    System.out.printf("id: %s\tname: %s\tdescription: %s\tdate: %s%s",
                            item.getId(),
                            item.getName(),
                            item.getDescription(),
                            item.getCreate(),
                            System.lineSeparator());
                    System.out.println("=================================================");
                }
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Find items by name.");
        }

    }

    private class Exit implements UserAction {

        @Override
        public int key() {
            return EXIT;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Exit");
        }

    }

}
