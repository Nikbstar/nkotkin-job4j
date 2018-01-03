package ru.nik66.start;

import ru.nik66.models.Item;

public class Tracker {

    private Item[] items = new Item[10];
    private int position = 0;

    public void add(Item item) {
        this.items[this.position++] = item;
    }

    public void replace(Item item) {
        for (int iterator = 0; iterator < this.position; iterator++) {
            if (this.items[iterator] != null && this.items[iterator].getId().equals(item.getId())) {
                this.items[iterator] = item;
                break;
            }
        }
    }

    public boolean delete(String id) {
        boolean result = false;
        for (int iterator = 0; iterator < this.position; iterator++) {
            if (this.items[iterator].getId().equals(id)) {
                if (iterator < this.position) {
                    System.arraycopy(this.items, iterator + 1, this.items, iterator, this.position - iterator);
                }
                this.position--;
                result = true;
                break;
            }
        }
        return result;
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int iterator = 0; iterator < this.position; iterator++) {
            result[iterator] = this.items[iterator];
        }
        return result;
    }

    public Item[] findByName(String name) {
        // I don't like this shit...
        Item[] tmpItems = new Item[this.position];
        Item[] result = null;
        int index = 0;

        for (int iterator = 0; iterator < tmpItems.length; iterator++) {
            if (this.items[iterator] != null && this.contains(this.items[iterator].getName(), name)) {
                tmpItems[index++] = this.items[iterator];
            }
        }
        if (index > 0) {
            result = new Item[index];
            for (int iterator = 0; iterator < result.length; iterator++) {
                result[iterator] = tmpItems[iterator];
            }
        }

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
