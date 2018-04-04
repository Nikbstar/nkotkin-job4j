package ru.nik66;

public class TicTacToe {

    private final int[][] values;

    public TicTacToe(final int[][] values) {
        this.values = values;
    }

    private boolean threeEquals(int a, int b, int c) {
        return a == b && a == c;
    }

    private boolean checkCol() {
        boolean result = false;
        main: for (int i = 0; i <= this.values.length - 3; i++) {
            for (int j = 0; j < this.values[i].length; j++) {
                if (threeEquals(this.values[i][j], this.values[i + 1][j], this.values[i + 2][j])) {
                    result = true;
                    break main;
                }
            }
        }
        return result;
    }

    private boolean checkRow() {
        boolean result = false;
        main: for (int[] value : this.values) {
            for (int j = 0; j <= value.length - 3; j++) {
                if (threeEquals(value[j], value[j + 1], value[j + 2])) {
                    result = true;
                    break main;
                }
            }
        }
        return result;
    }

    private boolean checkDiag() {
        boolean result = false;
        main: for (int i = 0; i <= this.values.length - 3; i++) {
            for (int j = 0; j <= this.values[i].length - 3; j++) {
                if (threeEquals(this.values[i][j], this.values[i + 1][j + 1], this.values[i + 2][j + 2])
                        || threeEquals(this.values[i + 2][j], this.values[i + 1][j + 1], this.values[i][j + 2])) {
                    result = true;
                    break main;
                }
            }
        }
        return result;
    }

    public boolean hasWinner() {
        return this.checkCol() || this.checkRow() || checkDiag();
    }

}
