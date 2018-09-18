package ru.nik66.calculator;

/**
 * Calculate ideal weight.
 */
public class Fit {

    /**
     * The method calculate ideal weight for man.
     * @param height man's height.
     * @return man's ideal weight.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * The method calculate ideal weight for woman.
     * @param height woman's height.
     * @return woman's ideal weight.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }

}
