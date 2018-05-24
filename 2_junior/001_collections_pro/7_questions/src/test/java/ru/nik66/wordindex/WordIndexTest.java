package ru.nik66.wordindex;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordIndexTest {

    private WIndexable wordIndex;

    @Before
    public void initialize() {
        this.wordIndex = new WordIndex();
        this.wordIndex.loadFile("WITest.txt");
    }

    @Test
    public void whenLoadFileAndGetIndexesThenReturnSetOfPositions() {
        Set<Long> actual = this.wordIndex.getIndexes4Word("one");
        Set<Long> expected = new HashSet<>();
        expected.add(0L);
        expected.add(8L);
        expected.add(18L);
        expected.add(28L);
        expected.add(38L);
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("four");
        expected = new HashSet<>();
        expected.add(23L);
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("six");
        expected = new HashSet<>();
        expected.add(43L);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenGetIndexesFromWrongWordThenReturnNull() {
        Set<Long> actual = this.wordIndex.getIndexes4Word("abc");
        Set<Long> expected = null;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTestEqualsWithStringIndexOfThen() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("WITest.txt"))) {
            while (fileReader.ready()) {
                sb.append(fileReader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Long> actual = this.wordIndex.getIndexes4Word("two");
        Set<Long> expected = new HashSet<>();
        expected.add((long) sb.toString().indexOf("two"));
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("three");
        expected = new HashSet<>();
        expected.add((long) sb.toString().indexOf("three"));
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("four");
        expected = new HashSet<>();
        expected.add((long) sb.toString().indexOf("four"));
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("five");
        expected = new HashSet<>();
        expected.add((long) sb.toString().indexOf("five"));
        assertThat(actual, is(expected));

        actual = this.wordIndex.getIndexes4Word("six");
        expected = new HashSet<>();
        expected.add((long) sb.toString().indexOf("six"));
        assertThat(actual, is(expected));
    }
}
