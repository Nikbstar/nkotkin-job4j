package ru.nik66.figures;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.Board;
import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnTest {

    private Board board;

    @Before
    public void init() {
        this.board = new Board();
    }

    @Test
    public void whenThePawnToMoveFromSeventhLineAtOnePositionThenItIsMoved() throws Exception {
        this.board.figures[6][6] = new Pawn(new Cell(6, 6));
        boolean actual = board.move(new Cell(6, 6), new Cell(5, 6));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenThePawnToMoveFromSecondLineAtTwoPositionThenItIsMoved() throws Exception {
        this.board.figures[6][6] = new Pawn(new Cell(6, 6));
        boolean actual = board.move(new Cell(6, 6), new Cell(4, 6));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenThePawnToMoveFromSecondLineAtThreePositionThenThrowImpossibleMoveException() throws Exception {
        this.board.figures[6][6] = new Pawn(new Cell(6, 6));
        board.move(new Cell(6, 6), new Cell(3, 6));

    }

    @Test
    public void whenThePawnToMoveFromThirdLineAtOnePositionThenItIsMoved() throws Exception {
        this.board.figures[5][6] = new Pawn(new Cell(5, 6));
        boolean actual = board.move(new Cell(5, 6), new Cell(4, 6));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenThePawnToMoveFromThirdLineAtTwoPositionThenThrowImpossibleMoveException() throws Exception {
        this.board.figures[5][6] = new Pawn(new Cell(5, 6));
        board.move(new Cell(5, 6), new Cell(3, 6));
    }

}
