package ru.nik66;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {
        return new Iterator<Integer>() {

            private Iterator<Integer> next = its.hasNext() ? its.next() : null;
            private Iterator<Integer> tmp = its.hasNext() ? its.next() : null;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (this.next != null && (this.next.hasNext() || (this.tmp != null && this.tmp.hasNext()))) {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() {
                int result;
                if (this.tmp == null && !this.next.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (!this.next.hasNext()) {
                    this.next = this.tmp;
                    this.tmp = its.hasNext() ? its.next() : null;
                }
                result = this.next.next();
                return result;
            }
        };
    }
}
