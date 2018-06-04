package ru.nik66.blqueue;

/**
 * Producer class
 */
public class Producer implements Runnable {

    /**
     * Simple Blocking Queue generic by Integer.
     */
    private final SimpleBlockingQueue<Integer> queue;
    /**
     * Data.
     */
    private final int data;

    /**
     * Constructor with args.
     * @param data int data.
     * @param queue Simple Blocking Queue.
     */
    public Producer(final int data, final SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
        this.data = data;
    }

    /**
     * Put data into queue and print that one and queue size.
     */
    @Override
    public void run() {
        try {
            this.queue.offer(this.data);
            System.out.printf("Producer - Data: %d; Size: %d%s", this.data, this.queue.size(), System.lineSeparator());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
