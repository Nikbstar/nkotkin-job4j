package ru.nik66.chessgui.figures.white;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenWhiteTest {

    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
        this.logic.getFigures()[0] = new QueenWhite(Cell.D4);
    }

    @Test
    public void whenTheQueenToMoveInXThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.A4);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveInXBackThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.H4);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveInYThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.D1);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveInYBackThenItIsMoved() throws Exception {

        boolean actual = logic.move(Cell.D4, Cell.D8);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.A1);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.A7);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.H8);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.G1);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheQueenToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.A8);
    }

}
