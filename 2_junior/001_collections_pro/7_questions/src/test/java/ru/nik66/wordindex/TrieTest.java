package ru.nik66.wordindex;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrieTest {

    final Trie trie = new Trie();

    @Before
    public void initialize() {
        final List<String> setOfStrings = new ArrayList<>();
        setOfStrings.add("pqrs");
        setOfStrings.add("pprt");
        setOfStrings.add("psst");
        setOfStrings.add("qqrs");
        setOfStrings.add("pqrs");
        setOfStrings.forEach(s -> trie.insert(s));
    }

    @Test
    public void whenInsertAndQueryThenReturnSetOfPositions() {
        Set<Long> actual = this.trie.query("pqrs");
        Set<Long> excepted = new HashSet<>();
        excepted.add(0L);
        excepted.add(4L);
        assertThat(actual, is(excepted));
    }

    @Test
    public void whenWrongQueryThenReturnNull() {
        Set<Long> actual = this.trie.query("abcd");
        Set<Long> excepted = null;
        assertThat(actual, is(excepted));
    }

}
