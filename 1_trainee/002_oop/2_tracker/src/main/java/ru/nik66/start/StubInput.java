package ru.nik66.start;

import java.util.List;

public class StubInput implements Input {

    private List<String> answers;
    private int position = 0;

    @Override
    public String ask(String question) {
        return this.answers.get(this.position++);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        //throw new UnsupportedOperationException("Unsupported operation");
        return Integer.valueOf(this.answers.get(this.position++));
    }

    public StubInput(List<String> answers) {
        this.answers = answers;
    }

}
