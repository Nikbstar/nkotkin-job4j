package ru.nik66.blqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    /*
    Result:
        Producer - Data: 5; Size: 1
        Consumer - Data: 5; Size: 0
        Producer - Data: 7; Size: 1
        Consumer - Data: 7; Size: 0
     */
    @Test
    public void whenSizeIsOneThenSecondProducerWaitingForConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread p1 = new Thread(new Producer(5, queue));
        Thread p2 = new Thread(new Producer(7, queue));
        Thread c1 = new Thread(new Consumer(queue));
        Thread c2 = new Thread(new Consumer(queue));

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();
        c1.join();
        c2.join();
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(() -> {
            IntStream.range(0, 5).forEach((value) -> {
                try {
                    queue.offer(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        Thread consumer = new Thread(() -> {
            while (queue.size() != 0 || !Thread.currentThread().isInterrupted()) {
                try {
                    buffer.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

}
