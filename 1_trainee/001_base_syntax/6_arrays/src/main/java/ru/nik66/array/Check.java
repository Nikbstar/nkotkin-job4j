package ru.nik66.array;

/**
 * The class checks the boolean array for mono elements.
 */
public class Check {

    /**
     * The method checks the array "data" for mono elements.
     * @param data
     * @return
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] != data[i + 1]) {
                result = false;
                break;
            }
        }
        return result;
    }

}
