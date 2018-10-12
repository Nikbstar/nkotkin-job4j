package ru.nik66.collstat;

import java.util.*;
import java.util.stream.Collectors;

public class Store {

    public Info diff(List<User> previous, List<User> current) {
        return new Info(
                this.different(previous, current),
                this.changeCount(previous, current),
                this.different(current, previous)
        );
    }

    private int different(List<User> previous, List<User> current) {
        Set<User> tmp = new HashSet<>(previous);
        tmp.addAll(current);
        return tmp.size() - previous.size();
    }

    private int changeCount(List<User> previous, List<User> current) {
        Map<Integer, String> tmp = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        return (int) previous.stream().filter((user) -> !tmp.getOrDefault(user.getId(), user.getName()).equals(user.getName())).count();
    }

}
