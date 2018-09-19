package ru.nik66.array;

/**
 * The class sorts the array.
 */
public class BubbleSort {

    /**
     * The method sorts the array by bubble sort.
     * @param array array.
     * @return sorted array.
     */
    public int[] sort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

}
