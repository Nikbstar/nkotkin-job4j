package ru.nik66;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private int[][] value;
    private int innerIndex = 0;
    private int outerIndex = 0;

    public MatrixIterator(int[][] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return this.outerIndex < this.value.length && this.innerIndex < this.value[outerIndex].length;
    }

    @Override
    public Object next() {
        if (this.outerIndex >= this.value.length || this.innerIndex >= this.value[outerIndex].length) {
            throw new NoSuchElementException();
        }
        int result = this.value[this.outerIndex][this.innerIndex++];
        if (this.innerIndex == this.value[this.outerIndex].length) {
            this.innerIndex = 0;
            this.outerIndex++;
        }
        return result;
    }

}
