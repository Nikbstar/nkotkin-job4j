package ru.nik66.collstat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        int result = 0;
        for (User old : previous) {
            if (current.contains(old) && !current.get(current.indexOf(old)).getName().equals(old.getName())) {
                result++;
            }
        }
        return result;
    }

}
