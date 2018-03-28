package ru.nik66.start;

import ru.nik66.models.Item;

import java.util.ArrayList;
import java.util.List;

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

    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().contains(name)) {
                result.add(item);
            }
        }
        return result;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

}
