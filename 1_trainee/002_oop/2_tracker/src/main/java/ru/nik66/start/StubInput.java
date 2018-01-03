package ru.nik66.start;

public class StubInput implements Input {

    private String[] answers;
    private int position = 0;

    @Override
    public String ask(String question) {
        return this.answers[this.position++];
    }

    public StubInput(String[] answers) {
        this.answers = answers;
    }

}
