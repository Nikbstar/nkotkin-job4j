package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreTest {

    @Test
    public void whenAddUserInUserStore() {
        Store<User> store = new UserStore();
        User user1 = new User("1");
        store.add(user1);

        User actual = store.findById("1");
        User expected = user1;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteRoleInRoleStoreThenReturnTrue() {
        RoleStore store = new RoleStore();
        Role role1 = new Role("1");
        store.add(role1);

        assertThat(store.delete("1"), is(true));
        Role actual = store.findById("1");
        Role expected = null;
        assertThat(actual, is(expected));

    }

    @Test
    public void whenDeleteWrongRoleInRoleStoreThenReturnFalse() {
        RoleStore store = new RoleStore();

        boolean actual = store.delete("1");
        boolean expected = false;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenReplaceUserInUserStoreThenReturnTrue() {
        Store<User> store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        store.add(user1);

        assertThat(store.replace("1", user2), is(true));
        User actual = store.findById("1");
        User expected = null;
        assertThat(actual, is(expected));
        actual = store.findById("2");
        expected = user2;
        assertThat(actual, is(expected));
    }
}
