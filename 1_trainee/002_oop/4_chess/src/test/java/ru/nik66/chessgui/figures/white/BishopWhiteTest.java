package ru.nik66.chessgui.figures.white;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopWhiteTest {

    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
        this.logic.getFigures()[0] = new BishopWhite(Cell.D4);
    }

    @Test
    public void whenTheBishopToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.C3);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheBishopToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.C5);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheBishopToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.E5);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheBishopToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.E3);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheBishopToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.D5);
    }

}
