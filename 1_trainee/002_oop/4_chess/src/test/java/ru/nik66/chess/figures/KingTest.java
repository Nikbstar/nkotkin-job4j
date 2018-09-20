package ru.nik66.chess.figures;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.Board;
import ru.nik66.chess.Cell;
import ru.nik66.chess.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingTest {

    private Board board;

    @Before
    public void init() {
        this.board = new Board();
        this.board.figures[4][4] = new King(new Cell(4, 4));
    }

    @Test
    public void whenTheKingToMoveInXThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 4));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveInXBackThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 4));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveInYThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(4, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveInYBackThenItIsMoved() throws Exception {

        boolean actual = board.move(new Cell(4, 4), new Cell(4, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(3, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 5));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = board.move(new Cell(4, 4), new Cell(5, 3));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheKingToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        board.move(new Cell(4, 4), new Cell(4, 6));
    }

}