package ru.nik66.htmlcssjs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.nik66.htmlcssjs.model.Sex;
import ru.nik66.htmlcssjs.model.User;

import java.io.IOException;

public class ValidateTest {

    @Test
    public void whenThen() throws IOException {
        User user = new User(1, "nik", "kot", Sex.MALE, "It's a person!");
        String json = new ObjectMapper().writeValueAsString(user);
        System.out.println(json);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        User jUser = new ObjectMapper().readValue(json, User.class);
        System.out.println(jUser.toString());
    }

}