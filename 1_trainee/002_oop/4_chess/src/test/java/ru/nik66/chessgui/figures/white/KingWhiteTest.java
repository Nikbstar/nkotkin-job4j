package ru.nik66.chessgui.figures.white;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.Logic;
import ru.nik66.chessgui.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingWhiteTest {
    
    private Logic logic;

    @Before
    public void init() {
        this.logic = new Logic();
        this.logic.getFigures()[0] = new KingWhite(Cell.D4);
    }

    @Test
    public void whenTheKingToMoveInXThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.D5);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveInXBackThenItIsMoved() throws Exception {
        boolean actual = logic.move(Cell.D4, Cell.E5);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveInYThenItIsMoved() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.E4);
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveInYBackThenItIsMoved() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.E3);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtFirstWayThenItIsMoved() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.D3);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtSecondWayThenItIsMoved() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.C3);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtThirdWayThenItIsMoved() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.C4);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheKingToMoveAtFourthWayThenItIsMoved() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.C5);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenTheKingToMoveAtWrongWayThenThrowImpossibleMoveException() throws Exception {
        boolean actual = this.logic.move(Cell.D4, Cell.D6);
    }

}
