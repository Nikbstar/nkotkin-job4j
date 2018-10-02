package ru.nik66.mathutil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FuncCalc {

    private List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int iterator = start; iterator <= end; iterator++) {
            result.add(func.apply((double) iterator));
        }
        return result;
    }

    public List<Double> line(int start, int end) {
        return diapason(start, end, n -> n);
    }

    public List<Double> quadratic(int start, int end) {
        return diapason(start, end, n -> Math.pow(n, 2));
    }

    public List<Double> log(int start, int end) {
        return diapason(start, end, Math::log);
    }
}
