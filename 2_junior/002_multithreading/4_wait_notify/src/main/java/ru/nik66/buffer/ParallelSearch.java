package ru.nik66.buffer;

import ru.nik66.blqueue.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        consumer.start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    queue.offer(i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            consumer.interrupt();
        }).start();
    }

}
