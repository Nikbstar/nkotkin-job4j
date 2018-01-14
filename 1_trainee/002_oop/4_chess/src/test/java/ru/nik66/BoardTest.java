package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import ru.nik66.exceptions.FigureNotFoundException;
import ru.nik66.exceptions.OccupiedWayException;
import ru.nik66.figures.Figure;
import ru.nik66.figures.Rook;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class BoardTest {


    private Board board;

    @Before
    public void init() {
        this.board = new Board();
    }

    @Test
    public void whenTheFigureMoveThenOldPositionIsNull() throws Exception {
        this.board.figures[0][0] = new Rook(new Cell(0, 0));
        board.move(new Cell(0, 0), new Cell(3, 0));

        Figure actual = this.board.figures[0][0];
        Figure expected = null;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheFigureMoveThenItWillIsAtNewPosition() throws Exception {
        this.board.figures[0][0] = new Rook(new Cell(0, 0));
        board.move(new Cell(0, 0), new Cell(3, 0));

        Figure actual = this.board.figures[3][0];
        Figure expected = null;

        assertThat(actual, not(expected));
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenNoFigureForMoveThenThrowFigureNotFoundException() throws Exception {
        board.move(new Cell(0, 0), new Cell(1, 0));
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupiedThenThrowOccupiedWayException() throws Exception {
        this.board.figures[0][0] = new Rook(new Cell(0, 0));
        this.board.figures[1][0] = new Rook(new Cell(1, 0));

        board.move(new Cell(0, 0), new Cell(2, 0));
    }

}
