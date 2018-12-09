package ru.nik66.crudservlet.store;

import org.junit.After;
import org.junit.Test;
import ru.nik66.crudservlet.model.City;
import ru.nik66.crudservlet.model.Country;
import ru.nik66.crudservlet.model.Role;
import ru.nik66.crudservlet.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DbStoreTest {

    private Store store = DbStore.getInstance();

    @After
    public void cleanTable() {
        store.clear();
    }

    @Test
    public void whenUpdate() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User("a", "a", "a", Role.ADMIN, "a", now, Country.USA, City.NY);
        this.store.add(user);
        int id = this.store.findAll().get(0).getId();
        User updatedUser = new User(id, "b", "b", "b", Role.ADMIN, "b", now, Country.USA, City.NY);
        this.store.update(updatedUser);
        List<User> actual = this.store.findAll();
        List<User> expected = Collections.singletonList(updatedUser);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDelete() {
        User user = new User("a", "a", "a", Role.ADMIN, "a", LocalDateTime.now(), Country.USA, City.NY);
        this.store.add(user);
        int id = this.store.findAll().get(0).getId();
        this.store.delete(new User(id, null, null, null, null, null, null, null, null));
        List<User> actual = this.store.findAll();
        List<User> expected = Collections.emptyList();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindById() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User("a", "a", "a", Role.ADMIN, "a", now, Country.USA, City.NY);
        this.store.add(user);
        int id = this.store.findAll().get(0).getId();
        User actual = this.store.findById(id);
        User expected = new User(id, "a", "a", "a", Role.ADMIN, "a", now, Country.USA, City.NY);
        assertThat(actual, is(expected));
    }
}