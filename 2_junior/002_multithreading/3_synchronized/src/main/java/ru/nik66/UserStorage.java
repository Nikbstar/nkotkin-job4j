package ru.nik66;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (this.users.containsValue(user)) {
            this.users.remove(user.getId());
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = this.users.get(fromId);
        User to = this.users.get(toId);
        if (from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            result = true;
        }
        return result;
    }

    public synchronized Map<Integer, User> getUsers() {
        return users;
    }
}
