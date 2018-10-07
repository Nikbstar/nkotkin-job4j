package ru.nik66.collstat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreTest {

    @Test
    public void whenAddNewUserThenGetNewUsersCountReturnsOne() {
        List<User> listOne = new ArrayList<>();
        List<User> listTwo = new ArrayList<>();
        listOne.add(new User(1, "a"));
        listTwo.add(new User(1, "a"));
        listTwo.add(new User(2, "c"));
        Store store = new Store();
        Info info = store.diff(listOne, listTwo);
        assertThat(info.getNewUsersCount(), is(1));
    }

    @Test
    public void whenChangeUserThenGetChangedUsersCountReturnsOne() {
        List<User> listOne = new ArrayList<>();
        listOne.add(new User(1, "a"));
        List<User> listTwo = new ArrayList<>();
        listTwo.add(new User(1, "d"));
        Store store = new Store();
        Info info = store.diff(listOne, listTwo);
        assertThat(info.getChangedUsersCount(), is(1));
    }

    @Test
    public void whenDeleteUserThenGetDeletedUsersCountReturnsOne() {
        List<User> listOne = new ArrayList<>();
        listOne.add(new User(1, "a"));
        listOne.add(new User(2, "b"));
        List<User> listTwo = new ArrayList<>();
        listTwo.add(new User(1, "a"));
        Store store = new Store();
        Info info = store.diff(listOne, listTwo);
        assertThat(info.getDeletedUsersCount(), is(1));
    }

}