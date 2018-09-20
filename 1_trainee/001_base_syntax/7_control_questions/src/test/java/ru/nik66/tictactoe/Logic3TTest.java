package ru.nik66.tictactoe;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Logic3TTest {

    @Test
    public void whenHasXWinner() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T(true)}
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.isWinnerX();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHasXBackDiagonalWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(true)},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(true), new Figure3T(), new Figure3T()}
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.isWinnerX();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHasXHorizontalWinner() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()}
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.isWinnerX();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHasXVerticalWinner() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(true), new Figure3T(), new Figure3T()}
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.isWinnerX();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHasOWinner() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(), new Figure3T()},
                {new Figure3T(false), new Figure3T(true), new Figure3T()},
                {new Figure3T(false), new Figure3T(), new Figure3T(true)}
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.isWinnerO();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHasGas() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T(true)},
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.hasGap();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHasNoGas() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(false), new Figure3T(false)},
        };
        Logic3T logic = new Logic3T(table);
        boolean actual = logic.hasGap();
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}
