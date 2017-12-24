package ru.nik66;

public class Factorial {

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
