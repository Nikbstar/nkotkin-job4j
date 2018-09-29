package ru.nik66.chessgui.figures.black;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PawnBlackTest {


    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
    }

    @Test
    public void whenMoveFromSeventhLineAtOnePositionThenItIsMoved() throws Exception {
        this.logic.getFigures()[0] = new PawnBlack(Cell.A7);
        boolean actual = logic.move(Cell.A7, Cell.A6);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveFromSeventhLineAtTwoPositionsThenItIsMoved() throws Exception {
        this.logic.getFigures()[0] = new PawnBlack(Cell.A7);
        boolean actual = logic.move(Cell.A7, Cell.A5);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveFromNotSeventhLineAtTwoPositionsThenItIsMoved() throws Exception {
        this.logic.getFigures()[0] = new PawnBlack(Cell.A6);
        boolean actual = logic.move(Cell.A6, Cell.A4);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}
