package ru.nik66.blqueue;

import org.junit.Test;

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

}
