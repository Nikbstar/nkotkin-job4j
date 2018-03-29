package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUserTest {

    private User user1;
    private User user2;

    @Before
    public void init() {
        this.user1 = new User("Kolya", 22);
        this.user2 = new User("Kat", 11);
    }

    @Test
    public void whenSortUsersByAgeThenReturnSortedSet() {
        List<User> users = Arrays.asList(user1, user2);
        SortUser sort = new SortUser();
        Set<User> actual = sort.sort(users);
        Set<User> expected = new TreeSet<>();
        expected.add(this.user2);
        expected.add(this.user1);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSortUsersByAgeThenMinus() {
        assertThat(user1.compareTo(user2), is(11));
    }

    @Test
    public void whenSortByNameLength() {
        List<User> users = Arrays.asList(this.user1, this.user2);
        SortUser sort = new SortUser();
        List<User> actual = sort.sortNameLength(users);
        List<User> expected = Arrays.asList(this.user2, this.user1);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSortByAllFields() {
        User user3 = new User("Kolya", 15);
        User user4 = new User("Kat", 55);
        List<User> users = Arrays.asList(this.user1, this.user2, user3, user4);
        SortUser sort = new SortUser();
        List<User> actual = sort.sortByAllFields(users);
        List<User> expected = Arrays.asList(this.user2, user4, user3, this.user1);
        assertThat(actual, is(expected));
    }

}
