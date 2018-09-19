package ru.nik66.array;

/**
 * Work with strings.
 */
public class ArrayChar {
    /**
     * The string as array of chars.
     */
    private char[] data;

    /**
     * The constructor takes the string and save it to char array field "data".
     * @param line string.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * The method checks that our string starts with prefix "prefix".
     * @param prefix prefix.
     * @return true if the string starts with prefix "prefix".
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
            if (value[i] != this.data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
