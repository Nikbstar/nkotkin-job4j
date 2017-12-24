package ru.nik66.bot;

public class DummyBot {

    public String answer(String question) {
        String rsl = "I don't know what you want! Please, ask another question.";
        if ("Hi, bot".equals(question)) {
            rsl = "Hi, smart ass.";
        } else if ("bye".equals(question)) {
            rsl = "See you later.";
        }
        return rsl;
    }

}
