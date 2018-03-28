package ru.nik66;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {

    @Test
    public void whenThen() {
        List<User> users = new ArrayList<>();
        UserConvert convert = new UserConvert();
        User user1 = new User(111, "Kolya", "Eburg");
        User user2 = new User(134, "Katya", "Moscow");
        users.add(user1);
        users.add(user2);
        HashMap<Integer, User> actual = convert.process(users);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(user1.getId(), user1);
        expected.put(user2.getId(), user2);
        assertThat(actual, is(expected));
    }

}
