package ru.nik66;

import java.util.Comparator;
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

    // 1) public List<User> sortNameLength (List<User>) - в этом методе необходимо определить
    // Comparator для метода Collections.sort и отсортировать List<User> по длине имени.
    public List<User> sortNameLength(List<User> users) {

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return users;
    }

    // 2) public List<User> sortByAllFields (List<User>) - в этом методе необходимо определить
    // Comparator для метода Collections.sort и отсортировать List<User> по обоим полям, сначала
    // сортировка по имени в лексикографическом порядке, потом по возрасту.
    public List<User> sortByAllFields(List<User> users) {

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return (result != 0) ? result : o1.getAge() - o2.getAge();
            }
        });
        return users;
    }

}
