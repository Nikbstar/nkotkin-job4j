package ru.nik66.loop;

/**
 * The class counts the sum of even numbers in the range.
 */
public class Counter {

    /**
     * The method counts the sum of even numbers in the range between start and finish.
     * @param start first number.
     * @param finish last number.
     * @return sum of even numbers.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return result;
    }

}
