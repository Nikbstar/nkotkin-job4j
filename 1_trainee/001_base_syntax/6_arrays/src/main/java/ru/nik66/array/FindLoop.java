package ru.nik66.array;

/**
 * The class finds the element into the array and returns it's index or -1 if the element is doesn't find.
 */
public class FindLoop {

    /**
     * The method finds the element "el" into array "data" and returns index or -1 if the element is doesn't find.
     * @param data array with elements.
     * @param el needed element.
     * @return index of element.
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                result = i;
                break;
            }
        }
        return result;
    }

}
