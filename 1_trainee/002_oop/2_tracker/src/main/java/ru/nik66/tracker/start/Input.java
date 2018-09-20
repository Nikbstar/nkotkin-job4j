package ru.nik66.tracker.start;

import java.util.List;

public interface Input {

    String ask(String question);

    int ask(String question, List<Integer> range);

}
