package ru.nik66.wordindex;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordIndexTest {

    WIndexable wordIndex;

    @Before
    public void initialize() {
        this.wordIndex = new WordIndex();
        this.wordIndex.loadFile("WITest.txt");
    }

    @Test
    public void whenLoadFileAndGetIndexesThenReturnSetOfPositions() {
        Set<Integer> actual = this.wordIndex.getIndexes4Word("one");
        Set<Integer> expected = new HashSet<>();
        expected.add(0);
        expected.add(2);
        expected.add(4);
        expected.add(6);
        expected.add(8);
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("four");
        expected = new HashSet<>();
        expected.add(5);
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("six");
        expected = new HashSet<>();
        expected.add(9);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenGetIndexesFromWrongWordThenReturnNull() {
        Set<Integer> actual = this.wordIndex.getIndexes4Word("abc");
        Set<Integer> expected = null;
        assertThat(actual, is(expected));
    }

}
