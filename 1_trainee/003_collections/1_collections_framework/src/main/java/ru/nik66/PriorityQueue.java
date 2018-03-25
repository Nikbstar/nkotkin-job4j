package ru.nik66;

import java.util.LinkedList;

public class PriorityQueue {

    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (this.tasks.size() != 0) {
            for (int i = 0; i < this.tasks.size(); i++) {
                if (task.getPriority() < this.tasks.get(i).getPriority()) {
                    this.tasks.add(i, task);
                    return;
                }
            }
        }
        this.tasks.add(task);
    }

    public Task take() {
        return this.tasks.poll();
    }

}
