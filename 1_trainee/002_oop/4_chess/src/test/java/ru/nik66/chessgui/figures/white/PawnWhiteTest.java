package ru.nik66.chessgui.figures.white;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.black.PawnBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnWhiteTest {


    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
    }

    @Test
    public void whenMoveFromSecondLineAtOnePositionThenItIsMoved() throws Exception {
        this.logic.getFigures()[0] = new PawnWhite(Cell.A2);
        boolean actual = logic.move(Cell.A2, Cell.A3);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveFromSecondLineAtTwoPositionsThenItIsMoved() throws Exception {
        this.logic.getFigures()[0] = new PawnWhite(Cell.A2);
        boolean actual = logic.move(Cell.A2, Cell.A4);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveFromNotSecondLineAtTwoPositionsThenItIsMoved() throws Exception {
        this.logic.getFigures()[0] = new PawnWhite(Cell.A3);
        boolean actual = logic.move(Cell.A3, Cell.A5);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}
