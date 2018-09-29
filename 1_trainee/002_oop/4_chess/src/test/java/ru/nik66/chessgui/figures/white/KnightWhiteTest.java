package ru.nik66.chessgui.figures.white;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightWhiteTest {

    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
        this.logic.getFigures()[0] = new KnightWhite(Cell.D4);
    }

    @Test
    public void whenTheKnightToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.C2);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.E2);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.F3);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.F5);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtFifthWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.E6);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtSixthWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.C6);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtSeventhWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.B5);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtEighthWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.B3);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheKingToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.D5);
    }

}
