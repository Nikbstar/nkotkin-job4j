package ru.nik66.array;

/**
 * The class fills array with square numbers from 1 to bound.
 */
public class Square {

    /**
     * The method fills and returns array with square numbers from 1 to bound.
     * @param bound last number.
     * @return array with squared numbers.
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];

        for (int i = 0; i < bound; i++) {
            result[i] = (i + 1) * (i + 1);
        }
        return result;
    }

}
