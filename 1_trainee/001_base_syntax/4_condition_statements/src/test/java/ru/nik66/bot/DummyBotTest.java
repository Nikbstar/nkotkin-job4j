package ru.nik66.bot;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DummyBotTest {

    @Test
    public void whenAskHiBotThenAnswerHiSmartAss() throws Exception {
        DummyBot bot = new DummyBot();

        String expected = "Hi, smart ass.";
        String actual = bot.answer("Hi, bot");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAskByeThenAnswerSeeYouLater() throws Exception {
        DummyBot bot = new DummyBot();

        String expected = "See you later.";
        String actual = bot.answer("bye");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAskSomethingThenAnsewrIDontKnow() throws Exception {
        DummyBot bot = new DummyBot();

        String expected = "I don't know what you want! Please, ask another question.";
        String actual = bot.answer("something");

        assertThat(actual, is(expected));
    }

}
