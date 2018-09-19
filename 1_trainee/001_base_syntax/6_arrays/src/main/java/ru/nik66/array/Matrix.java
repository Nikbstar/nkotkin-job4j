package ru.nik66.array;

/**
 * The class makes the two-dimensional array by multiple table.
 */
public class Matrix {

    /**
     * The method makes the two-dimensional array by multiple table.
     * @param size table size.
     * @return array with multple table matrix.
     */
    public int[][] multiple(int size) {
        int[][] multipleTable = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                multipleTable[i][j] = (i + 1) * (j + 1);
            }
        }

        return multipleTable;
    }

}
