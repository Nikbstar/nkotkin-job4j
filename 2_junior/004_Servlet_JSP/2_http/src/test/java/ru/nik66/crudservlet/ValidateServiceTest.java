package ru.nik66.crudservlet;

import org.junit.Test;
import ru.nik66.crudservlet.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValidateServiceTest {

    private Validate validate = ValidateService.getInstance();

    @Test
    public void whenAddUpdateAndDeleteUser() {
        User user = new User("Name", "log", "email@address.ru", LocalDateTime.now());
        this.validate.add(user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate().toString());
        List<User> actual = this.validate.findAll();
        user.setId(actual.get(0).getId());
        List<User> excepted = Collections.singletonList(user);
        assertThat(actual, is(excepted));

        User newUser = new User(user.getId(), "newName", "login", "e@add.ru", user.getCreateDate());
        this.validate.update(newUser.getId(), newUser.getName(), newUser.getLogin(), newUser.getEmail());
        actual = this.validate.findAll();
        excepted = Collections.singletonList(newUser);
        assertThat(actual, is(excepted));

        assertThat(this.validate.findById(newUser.getId()), is(newUser));

        this.validate.delete(newUser.getId());
        assertThat(this.validate.findAll(), is(Collections.emptyList()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongEmailAddressThenThrowsException() {
        this.validate.add("name", "log", "email.ru", LocalDateTime.now().toString());
    }
}