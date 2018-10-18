package ru.nik66.tracker.start;

public interface UserAction {

    int key();

    void execute(Input input, ITracker tracker);

    String info();

}
