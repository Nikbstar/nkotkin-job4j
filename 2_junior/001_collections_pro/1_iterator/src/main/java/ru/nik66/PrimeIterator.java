package ru.nik66;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {

    private final int[] numbers;
    private int index = 0;

    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.index; i < this.numbers.length; i++) {
            if (this.isPrimeNumber(this.numbers[i])) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int i = this.index;
        if (i == 0) {
            i = findIndex();
        }
        if (i >= this.numbers.length) {
            throw new NoSuchElementException();
        }
        this.index = i + 1;
        this.index = findIndex();
        return this.numbers[i];
    }

    private int findIndex() {
        int result = this.index;
        while (result < this.numbers.length && !this.isPrimeNumber(this.numbers[result])) {
            result++;
        }
        return result;
    }


    private boolean isPrimeNumber(int number) {
        boolean result = number != 1;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
