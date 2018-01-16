package ru.nik66.figures;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.Board;
import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenTest {

    private Board board;

    @Before
    public void init() {
        this.board = new Board();
        this.board.figures[4][4] = new Queen(new Cell(4, 4));
    }

    @Test
    public void whenTheQueenToMoveInXThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 4));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveInXBackThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 4));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveInYThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(4, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveInYBackThenItIsMoved() throws Exception {

        boolean actual = board.move(new Cell(4, 4), new Cell(4, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheQueenToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheQueenToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        board.move(new Cell(4, 4), new Cell(5, 6));
    }

}


