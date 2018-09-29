package ru.nik66.chessgui;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.exceptions.FigureNotFoundException;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chess.exceptions.OccupiedWayException;
import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.black.RookBlack;
import ru.nik66.chessgui.figures.white.RookWhite;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class LogicTest {

    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
    }

    @Test
    public void whenTheFigureMoveThenItWillIsAtNewPosition() throws Exception {
        this.logic.getFigures()[0] = new RookBlack(Cell.A1);
        logic.move(Cell.A1, Cell.D1);

        Cell actual = this.logic.getFigures()[0].position();
        Cell expected = Cell.D1;

        assertThat(actual, is(expected));
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenNoFigureForTheMoveThenThrowFigureNotFoundException() throws Exception {
        logic.move(Cell.A1, Cell.D1);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupiedThenThrowOccupiedWayException() throws Exception {
        this.logic.getFigures()[0] = new RookBlack(Cell.A1);
        this.logic.getFigures()[1] = new RookWhite(Cell.B1);
        this.logic.move(Cell.A1, Cell.C1);
    }

}
