package ru.nik66;

public class Matrix {

    public int[][] multiple(int size) {
        // TODO
        int[][] multipleTable = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                multipleTable[i][j] = (i + 1) * (j + 1);
            }
        }

        return multipleTable;
    }

}
