package ru.nik66.figures;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.Board;
import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {

    private Board board;

    @Before
    public void init() {
        this.board = new Board();
        this.board.figures[4][4] = new Knight(new Cell(4, 4));
    }

    @Test
    public void whenTheKnightToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(2, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(2, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 6));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 6));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtFifthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(6, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtSixthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(6, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtSeventhWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 2));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKnightToMoveAtEighthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 2));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheKingToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        board.move(new Cell(4, 4), new Cell(4, 6));
    }

}
