package ru.nik66.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BoardTest {

    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String eol = System.lineSeparator();

        String actual = board.paint(3, 3);
        String expected = String.format("X X%s X %sX X%s", eol, eol, eol);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
        String eol = System.lineSeparator();

        String actual = board.paint(5, 4);
        String expected = String.format("X X X%s X X %sX X X%s X X %s", eol, eol, eol, eol);

        assertThat(actual, is(expected));
    }

}
