package ru.nik66.figures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.nik66.Board;
import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

public class RookTest {

    private Board board;

    @Before
    public void init() {
        this.board = new Board();
    }

    @Test
    public void whenTheRookToMoveInXThenItIsMoved() throws Exception {
        this.board.figures[0][0] = new Rook(new Cell(0, 0));

        boolean actual = board.move(new Cell(0, 0), new Cell(3, 0));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheRookToMoveInXBackThenItIsMoved() throws Exception {
        this.board.figures[4][0] = new Rook(new Cell(4, 0));

        boolean actual = board.move(new Cell(4, 0), new Cell(1, 0));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheRookToMoveInYThenItIsMoved() throws Exception {
        this.board.figures[0][0] = new Rook(new Cell(0, 0));

        boolean actual = board.move(new Cell(0, 0), new Cell(0, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheRookToMoveInYBackThenItIsMoved() throws Exception {
        this.board.figures[0][4] = new Rook(new Cell(0, 4));

        boolean actual = board.move(new Cell(0, 4), new Cell(0, 1));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheRookToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        this.board.figures[0][0] = new Rook(new Cell(0, 0));
        board.move(new Cell(0, 0), new Cell(1, 1));
    }

}
