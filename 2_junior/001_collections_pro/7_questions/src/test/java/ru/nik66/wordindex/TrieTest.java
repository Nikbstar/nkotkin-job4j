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
        Set<Integer> actual = this.trie.query("pqrs");
        Set<Integer> excepted = new HashSet<>();
        excepted.add(0);
        excepted.add(4);
        assertThat(actual, is(excepted));
    }

    @Test
    public void whenWrongQueryThenReturnNull() {
        Set<Integer> actual = this.trie.query("abcd");
        Set<Integer> excepted = null;
        assertThat(actual, is(excepted));
    }

}
