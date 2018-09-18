package ru.nik66.blqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Blocking Queue class based by Linked List Queue.
 * Очередь, заточенная под потоки.
 * Класс задает размер очереди, при привышении которого
 * поток, пытающийся добавить следующие данные в очередь
 * блокируется и ждет, пока в очереди не появится
 * свободное место.
 * Если в очереди нет данных, то поток, который берет
 * данные из очереди блокируется и ждет, пока данные
 * в очереди не появятся.
 * @param <T> data type.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Linked List Queue.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * Размер очереди.
     */
    private final int capacity;

    /**
     * Конструктор без аргументов задает размер очереди
     * равный максимальному инту.
     */
    public SimpleBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Конструктор с аргументом принимает размер очереди.
     * @param capacity размер очереди.
     */
    public SimpleBlockingQueue(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong capacity value (must be > 0).");
        }
        this.capacity = capacity;
    }

    /**
     * Метод добавляет данные в очередь.
     * Если в очереди нет места, то поток переходит в режим
     * ожидания, пока место не появится.
     * @param value данные.
     * @throws InterruptedException Комманда на прерывание.
     */
    public void offer(final T value) throws InterruptedException {
        synchronized (this) {
            while (this.size() >= this.capacity) {
                wait();
            }
            this.queue.offer(value);
            notify();
        }
    }

    /**
     * Метод возвращает данные из очереди.
     * Если очередь пустая, то поток переходит в режим
     * ожидания появления данных в очереди.
     * @return данные.
     * @throws InterruptedException Комманда на прерывание.
     */
    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.size() <= 0) {
                wait();
            }
            T result = this.queue.poll();
            notify();
            return result;
        }
    }

    /**
     * Метод возвращает количество элементов в очереди.
     * @return количество элементов в очереди.
     */
    public int size() {
        synchronized (this) {
            return this.queue.size();
        }
    }

}