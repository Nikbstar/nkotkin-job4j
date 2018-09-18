package ru.nik66.max;


/**
 * Found maximum between numbers.
 */
public class Max {

    /**
     * The method found maximum between two numbers.
     * @param first first number.
     * @param second second number.
     * @return maximum number.
     */
    public int max(int first, int second) {
        return (first >= second) ? first : second;
    }

    /**
     * The method found maximum between three numbers.
     * @param first first number.
     * @param second second number.
     * @param third third number.
     * @return maximum number.
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }

}
