package ru.nik66.tracker.models;

import java.util.Random;

public class Item {

    private String id;
    private String name;
    private String description;
    private long create;
    private static final Random RN = new Random();

    public Item(String name, String description, long create) {
        this(String.valueOf(System.currentTimeMillis() + RN.nextInt()), name, description, create);
    }

    public Item(String id, String name, String description, long create) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setCreate(create);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreate() {
        return this.create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (create != item.create) {
            return false;
        }
        if (id != null ? !id.equals(item.id) : item.id != null) {
            return false;
        }
        return (name != null ? name.equals(item.name) : item.name == null) && (description != null ? description.equals(item.description) : item.description == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (create ^ (create >>> 32));
        return result;
    }
}
