package ru.nik66.chess.figures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.nik66.chess.Board;
import ru.nik66.chess.Cell;
import ru.nik66.chess.exceptions.ImpossibleMoveException;

public class BishopTest {

    private Board board;

    @Before
    public void init() {
        this.board = new Board();
        this.board.figures[4][4] = new Bishop(new Cell(4, 4));

    }

    @Test
    public void whenTheBishopToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheBishopToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheBishopToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheBishopToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheBishopToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        board.move(new Cell(4, 4), new Cell(4, 5));
    }

}
