package ru.nik66.tracker.start;

import ru.nik66.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tracker {

    private List<Item> items = new ArrayList<>();

    public void add(Item item) {
        this.items.add(item);
    }

    public void replace(Item item) {
        for (int iterator = 0; iterator < this.items.size(); iterator++) {
            if (this.items.get(iterator).getId().equals(item.getId())) {
                this.items.set(iterator, item);
                break;
            }
        }
    }

    public boolean delete(String id) {
        boolean result = false;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = this.items.remove(item);
                break;

            }
        }
        return result;
    }

    public List<Item> findAll() {
        return this.items;
    }

    public List<Item> findByName(final String name) {
        List<Item> result = new ArrayList<>();
        Predicate<Item> search = item -> item.getName().contains(name);
        this.items.forEach(item -> {
            if (search.test(item)) {
                result.add(item);
            }
        });
        return result;
    }

    public Item findById(final String id) {
        final Predicate<Item> search = item -> item.getId().equals(id);
        final Item[] result = new Item[1];
        result[0] = null;
        this.items.forEach(item -> {
            if (search.test(item)) {
                result[0] = item;
            }
        });
        return result[0];
    }

}
