package ru.nik66.tracker.start;

import ru.nik66.tracker.models.Item;

import java.util.List;

public interface ITracker {

    void add(Item item);
    void replace(Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String name);
    Item findById(String id);

}
