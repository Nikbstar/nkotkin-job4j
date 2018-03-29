package ru.nik66;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


// Необходимо создать модель User с полями name, age.
// Класс User должен реализовать интерфейс Comparable.
// В классе SortUser написать метод public Set<User> sort (List<User>),
// который будет возвращать TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
public class SortUser {

    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

}
