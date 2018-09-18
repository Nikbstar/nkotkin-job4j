package ru.nik66.converter;

/**
 * Currency converter.
 */
public class Converter {

    /**
     * Convert ruble to euro.
     * @param value ruble.
     * @return euro.
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Convert ruble to dollar.
     * @param value ruble.
     * @return dollar.
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

}
