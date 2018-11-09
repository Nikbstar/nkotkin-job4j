package ru.nik66.crudservlet.store;

import ru.nik66.crudservlet.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements Store {

    private final static MemoryStore INSTANCE = new MemoryStore();

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private static final AtomicInteger ID = new AtomicInteger(1);

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    private int generateId() {
        return ID.getAndIncrement();

    }

    @Override
    public void add(User user) {
        user.setId(this.generateId());
        this.users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        this.users.replace(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        this.users.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new CopyOnWriteArrayList<>(this.users.values());
    }

    @Override
    public User findById(int id) {
        return this.users.get(id);
    }

    @Override
    public void clear() {
        this.users.clear();
    }

}
