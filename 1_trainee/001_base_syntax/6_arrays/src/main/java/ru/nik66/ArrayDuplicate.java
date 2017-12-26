package ru.nik66;

import java.util.Arrays;

public class ArrayDuplicate {

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
