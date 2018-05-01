package ru.nik66;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenEqualsAndHashCodeIsNotOverride() {
        User user1 = new User("Masha", 0, new GregorianCalendar(2018, 4, 15));
        User user2 = new User("Masha", 0, new GregorianCalendar(2018, 4, 15));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, "1");
        map.put(user2, "2");

        System.out.println(map);
    }

}
