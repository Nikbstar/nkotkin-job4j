package ru.nik66.start;

import ru.nik66.models.Item;

public class StartUI {

    private static final String ADD = "0";
    private static final String ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String ID = "4";
    private static final String NAME = "5";
    private static final String EXIT = "6";

    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    private void showMenu() {
        System.out.println("~~~~~ Menu ~~~~~");
        System.out.println("0. Add new item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit items.");
        System.out.println("3. Delete item.");
        System.out.println("4. Find item by id.");
        System.out.println("5. Find items by name.");
        System.out.println("6. Exit");
    }

    public void init() {
        boolean exit = false;

        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItems();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (ID.equals(answer)) {
                this.findById();
            } else if (NAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    private void createItem() {
        System.out.println("~~~~~ Add New Item ~~~~~");
        String name = this.input.ask("Enter item name: ");
        String description = this.input.ask("Enter item description: ");
        Item item = new Item(name, description, 1L);
        this.tracker.add(item);
        System.out.println("~~~~~ New item id: " + item.getId() + " ~~~~~");
    }

    private void showAllItems() {
        System.out.println("~~~~~ Show All Items ~~~~~");
        for (Item item : this.tracker.findAll()) {
            System.out.printf("id: %s\tname: %s\tdescription: %s\t date: %s\n",
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getCreate());
            System.out.println("=================================================");
        }

    }

    private void editItems() {
        System.out.println("~~~~~ Edit Item ~~~~~");
        Item item = this.tracker.findById(this.input.ask("Enter item id: "));
        if (item == null) {
            System.out.println("~~~~~ Item not found! ~~~~~");
        } else {
            item.setName(this.input.ask("Enter item name:"));
            item.setDescription(this.input.ask("Enter item description: "));
            this.tracker.replace(item);
        }
    }

    private void deleteItem() {
        System.out.println("~~~~~ Delete Item ~~~~~");
        if (!this.tracker.delete(this.input.ask("Enter item id: "))) {
            System.out.println("~~~~~ Item not found! ~~~~~");
        }
    }

    private void findById() {
        System.out.println("~~~~~ Find Item By Id ~~~~~");
        Item item = this.tracker.findById(this.input.ask("Enter item id: "));
        if (item == null) {
            System.out.println("~~~~~ Item not found! ~~~~~");
        } else {
            System.out.printf("id: %s\tname: %s\tdescription: %s\t date: %s\n",
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getCreate());
            System.out.println("=================================================");
        }
    }

    private void findByName() {
        System.out.println("~~~~~ Find Item By Id ~~~~~");
        Item[] items = this.tracker.findByName(this.input.ask("Enter item name: "));
        if (items == null) {
            System.out.println("~~~~~ Items not found! ~~~~~");
        } else {
            for (Item item : items) {
                System.out.printf("id: %s\tname: %s\tdescription: %s\t date: %s\n",
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCreate());
                System.out.println("=================================================");
            }
        }
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

}
