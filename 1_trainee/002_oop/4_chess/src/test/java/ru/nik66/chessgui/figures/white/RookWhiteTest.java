package ru.nik66.chessgui.figures.white;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RookWhiteTest {

    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
        this.logic.getFigures()[0] = new RookWhite(Cell.D4);
    }

    @Test
    public void whenTheRookToMoveInXThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.D1);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheRookToMoveInXBackThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.D8);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheRookToMoveInYThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.A4);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheRookToMoveInYBackThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.H4);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheRookToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.B1);
    }

}
