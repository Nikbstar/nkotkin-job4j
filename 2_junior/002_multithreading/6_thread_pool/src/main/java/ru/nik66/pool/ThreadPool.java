package ru.nik66.pool;

import ru.nik66.blqueue.SimpleBlockingQueue;

import java.util.concurrent.RejectedExecutionException;

public class ThreadPool {

    private final static int SIZE = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(SIZE);
    private volatile boolean isWorking = true;

    public ThreadPool() {
        for (int i = 0; i < SIZE; i++) {
            new Thread(() -> {
                while (isWorking) {
                    try {
                        Runnable task = tasks.poll();
                        if (task != null) {
                            task.run();
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }).start();
        }
    }

    public void work(Runnable job) {
        if (!this.isWorking) {
            throw new RejectedExecutionException("The pool was shutdown!");
        }
        try {
                this.tasks.offer(job);
        } catch (InterruptedException e) {
            // DO NOTHING
        }
    }

    public void shutdown() {
        this.isWorking = false;
    }

}
