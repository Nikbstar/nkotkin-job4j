package ru.nik66.cache;

import java.util.concurrent.ConcurrentHashMap;

public class CacheBaseList {

    private ConcurrentHashMap<Integer, Base> values = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return this.values.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        this.values.computeIfPresent(model.getId(), (key, value) -> {
            if (values.get(model.getId()).getVersion() != model.getVersion()) {
                throw new OptimisticException("Can't update because version is wrong!");
            }
            return new Base(model.getId(), model.getVersion() + 1);
        });
        return true;
    }

    public boolean delete(Base model) {
        if (this.values.get(model.getId()).getVersion() != model.getVersion()) {
            throw new OptimisticException("Can't delete because version is wrong!");
        }
        this.values.remove(model.getId());
        return true;
    }

    public ConcurrentHashMap<Integer, Base> getValues() {
        return this.values;
    }
}
