package ru.nik66.array;

/**
 * The class turns back the array.
 */
public class Turn {

    /**
     * The method turns back the array "array".
     * @param array the array with the elements.
     * @return the turned back array.
     */
    public int[] back(int[] array) {
        int tmp;
        for (int i = 0; i < array.length - 1 - i; i++) {
            tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

}
