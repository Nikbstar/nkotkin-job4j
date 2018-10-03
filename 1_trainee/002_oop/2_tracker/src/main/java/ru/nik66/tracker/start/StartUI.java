package ru.nik66.tracker.start;

import java.util.ArrayList;
import java.util.List;

public class StartUI {

    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, System.out::println, this.tracker);
        int key;
        menu.init();
        List<Integer> range = new ArrayList<>();
        for (UserAction action : menu.getActions()) {
            range.add(action.key());
        }
        do {
            menu.show();
            key = input.ask("Select: ", range);
            menu.select(key);
        } while (key != MenuTracker.EXIT);
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }

}
