package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TicTacToeTest {

    @Test
    public void whenWinInDiagonalThenWinner() {
        TicTacToe game = new TicTacToe(
                new int[][] {
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 0, 1}
                }
        );
        boolean actual = game.hasWinner();
        assertThat(actual, is(true));
    }
}
