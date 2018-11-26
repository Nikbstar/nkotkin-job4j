package ru.nik66.crudservlet;

import org.junit.After;
import org.junit.Test;
import ru.nik66.crudservlet.model.Role;
import ru.nik66.crudservlet.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValidateServiceTest {

    private Validate validate = ValidateService.getInstance();

    @After
    public void clean() {
        this.validate.clear();
    }

    @Test
    public void whenAddUpdateAndDeleteUser() {
        User user = new User("Name", "log", "p", Role.ADMIN, "email@address.ru", LocalDateTime.now());
        this.validate.add(user.getName(), user.getLogin(), user.getPassword(), user.getRole().getName(), user.getEmail());
        List<User> actual = this.validate.findAll();
        user.setId(actual.get(0).getId());
        user.setCreateDate(actual.get(0).getCreateDate());
        List<User> excepted = Collections.singletonList(user);
        assertThat(actual, is(excepted));

        User newUser = new User(user.getId(), "newName", "login", "p", Role.ADMIN, "e@add.ru", user.getCreateDate());
        this.validate.update(newUser.getId(), newUser.getName(), newUser.getLogin(), newUser.getPassword(), user.getRole().getName(), newUser.getEmail());
        actual = this.validate.findAll();
        excepted = Collections.singletonList(newUser);
        assertThat(actual, is(excepted));

        assertThat(this.validate.findById(newUser.getId()), is(newUser));

        this.validate.delete(newUser.getId());
        assertThat(this.validate.findAll(), is(Collections.emptyList()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongEmailAddressThenThrowsException() {
        this.validate.add("name", "elog", "p", Role.ADMIN.getName(), "email.ru");
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenLoginIsDuplicateThenThrowsException() {
        this.validate.add("name", "alog", "p", Role.ADMIN.getName(), "e@mail.ru");
        this.validate.add("name1", "alog", "p", Role.ADMIN.getName(), "a@mail.ru");
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenEmailIsDuplicateThenThrowsException() {
        this.validate.add("name", "zlog", "p", Role.ADMIN.getName(), "t@mail.ru");
        this.validate.add("name1", "xlog", "p", Role.ADMIN.getName(), "t@mail.ru");
    }

}