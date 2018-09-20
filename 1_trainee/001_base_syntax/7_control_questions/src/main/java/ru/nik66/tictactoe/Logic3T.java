package ru.nik66.tictactoe;

public class Logic3T {

    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean hasGap() {
        boolean result = false;
        main: for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                if (!this.table[i][j].hasMarkX() && !this.table[i][j].hasMarkO()) {
                    result = true;
                    break main;
                }
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if (checkX(i, 0, 0, 1)) {
                result = true;
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < this.table.length; i++) {
                if (checkX(0, i, 1, 0)) {
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            result = checkX(0, 0, 1, 1) || checkX(0, this.table.length - 1, 1, -1);
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if (checkO(i, 0, 0, 1)) {
                result = true;
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < this.table.length; i++) {
                if (checkO(0, i, 1, 0)) {
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            result = checkO(0, 0, 1, 1) || checkO(0, this.table.length - 1, 1, -1);
        }
        return result;
    }

    private boolean checkX(int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            if (!this.table[startX][startY].hasMarkX()) {
                result = false;
                break;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result;
    }
    private boolean checkO(int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            if (!this.table[startX][startY].hasMarkO()) {
                result = false;
                break;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result;
    }
}
