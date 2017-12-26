package ru.nik66;

public class Turn {

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
