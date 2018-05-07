package ru.nik66;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {

    private final int[] numbers;
    private int index = 0;

    public EvenNumbersIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.index; i < this.numbers.length; i++) {
            if (this.numbers[i] % 2 == 0) {
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
        while (result < this.numbers.length && this.numbers[result] % 2 != 0) {
            result++;
        }
        return result;
    }
}
