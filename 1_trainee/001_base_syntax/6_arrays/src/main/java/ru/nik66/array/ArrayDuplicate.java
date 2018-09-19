package ru.nik66.array;

import java.util.Arrays;

/**
 * The class works with the duplicates into the array.
 */
public class ArrayDuplicate {

    /**
     * The method removes duplicates from the array "array".
     * @param array array.
     * @return copy of array without duplicates.
     */
    public String[] remove(String[] array) {
        int len = array.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j != len; j++) {
                if (array[i].equals(array[j])) {
                   array[j] = array[len - 1];
                   j--;
                   len--;
                }
            }
        }

        return Arrays.copyOf(array, len);
    }

}
