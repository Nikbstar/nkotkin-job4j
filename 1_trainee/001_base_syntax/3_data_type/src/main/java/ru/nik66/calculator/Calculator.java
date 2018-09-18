package ru.nik66.calculator;

/**
 * Calculator.
 */
public class Calculator {

    /**
     * Result of calculation.
     */
    private double result;

    /**
     * The method add first param to second.
     * @param first first param.
     * @param second second param.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * The method subtract second param from first.
     * @param first first param.
     * @param second second param.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * The method div first param on second.
     * @param first first param.
     * @param second second param.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * The method multiple first param on second.
     * @param first first param.
     * @param second second param.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * The method returns the result of calculation.
     * @return result of calculation.
     */
    public double getResult() {
        return this.result;
    }

}
