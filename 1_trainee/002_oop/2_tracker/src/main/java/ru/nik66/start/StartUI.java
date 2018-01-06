package ru.nik66.start;

public class StartUI {

    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.init();
        int key;

        do {
            menu.show();
            key = Integer.parseInt(input.ask("Select: "));
            menu.select(key);
        } while (key != MenuTracker.EXIT);
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

}
