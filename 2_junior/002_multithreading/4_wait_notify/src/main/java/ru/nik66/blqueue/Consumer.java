package ru.nik66.blqueue;

/**
 * Consumer.
 */
public class Consumer implements Runnable {

    /**
     * Simple Blocking Queue generic by Integer.
     */
    private final SimpleBlockingQueue<Integer> queue;

    /**
     * Constructor with args.
     * @param queue SimpleBlockingQueue.
     */
    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    /**
     * Take data from queue and print that one and queue size.
     */
    @Override
    public void run() {
        try {
            int data = this.queue.poll();
            System.out.printf("Consumer - Data: %d; Size: %d%s", data, this.queue.size(), System.lineSeparator());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
