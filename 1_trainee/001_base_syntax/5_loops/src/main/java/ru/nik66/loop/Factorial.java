package ru.nik66.loop;

/**
 * The class calculates the factorial.
 */
public class Factorial {

    /**
     * The method calculates the factorial of the number n.
     * @param n number.
     * @return factorial.
     */
    public int calc(int n) {
        int result = 1;
        if (n == 0) {
            n = 1;
        }
        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }

}
