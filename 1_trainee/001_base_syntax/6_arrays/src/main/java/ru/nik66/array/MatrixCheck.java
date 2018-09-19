package ru.nik66.array;

/**
 * The class checks the boolean matrix for the mono diagonals.
 */
public class MatrixCheck {

    /**
     * The method checks the boolean matrix "data" for the mono diagonals.
     * @param data boolean matrix.
     * @return true if diagonals are mono.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int lastElement = data.length - 1;
        for (int i = 0; i < lastElement; i++) {
            if ((data[i][i] != data[i + 1][i + 1]) || (data[i][lastElement - i] != data[i + 1][lastElement - i - 1])) {
                result = false;
                break;
            }
        }
        return result;
    }

}