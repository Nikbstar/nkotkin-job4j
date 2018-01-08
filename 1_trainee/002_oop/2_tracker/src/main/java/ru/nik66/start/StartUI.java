package ru.nik66.start;

public class StartUI {

    private int[] range;
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int key;

        menu.init();
        this.range = new int[menu.getActions().length];
        for (int i = 0; i < range.length; i++) {
            range[i] = menu.getActions()[i].key();
        }

        do {
            menu.show();
            key = input.ask("Select: ", range);
            menu.select(key);
        } while (key != MenuTracker.EXIT);
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }

}
