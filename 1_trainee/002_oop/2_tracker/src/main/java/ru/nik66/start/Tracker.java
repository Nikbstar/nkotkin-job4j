package ru.nik66.start;

import ru.nik66.models.Item;

public class Tracker {

    private Item[] items = new Item[10];
    private int position = 0;

    public void add(Item item) {
        this.items[this.position++] = item;
    }

    public void replace(Item item) {
        for (int iterator = 0; iterator < this.items.length; iterator++) {
            if (this.items[iterator] != null && this.items[iterator].getId().equals(item.getId())) {
                this.items[iterator] = item;
                break;
            }
        }
    }

    public void delete(String id) {
        for (int iterator = 0; iterator < this.items.length; iterator++) {
            if (this.items[iterator].getId().equals(id)) {
                if (iterator < this.position) {
                    System.arraycopy(this.items, iterator + 1, this.items, iterator, this.position - iterator);
                }
                this.position--;
                break;
            }
        }
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int iterator = 0; iterator < this.position; iterator++) {
            result[iterator] = this.items[iterator];
        }
        return result;
    }

    public Item[] findByName(String name) {
        Item[] tmpItems = new Item[this.position];
        int iterator = 0;
        for (Item item : this.items) {
            if (item != null && this.contains(item.getName(), name)) {
                tmpItems[iterator++] = item;
            }
        }
        Item[] result = new Item[iterator];
        for (int index = 0; index < result.length; index++) {
            result[index] = tmpItems[index];
        }
        tmpItems = null;
        return result;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public boolean contains(String origin, String sub) {
        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        boolean result = false;
        int subIterator = 0;
        for (int originIterator = 0; originIterator <= originArray.length - subArray.length; originIterator++) {
            while (subIterator < subArray.length) {
                if (originArray[originIterator + subIterator] == subArray[subIterator]) {
                    subIterator++;
                } else {
                    subIterator = 0;
                    break;
                }
            }
            if (subIterator == subArray.length) {
                result = true;
                break;
            }
        }
        return result;
    }

}
