package ru.nik66;

public class SyncProblems {

    /**
     * Процессор производит какие то операции с полен visibility
     * не вынимая  из кеша, и второй поток не видит этого.
     */
    private int visibility;
    /**
     * Когда мы запретили кеширование, если операция не атомарна
     * (в одно действие) то пока один процессор не успел доделать
     * все операции и выдать результат, второй процессор взялся
     * за работу с еще необновленными данными.
     */
    private volatile int raceCondition;

    public int doVisCount() {
        Thread v1 = new Thread(new Visibility());
        Thread v2 = new Thread(new Visibility());
        v1.start();
        v2.start();
        while (v1.isAlive() || v2.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.visibility;
    }

    public int doRaceCount() {
        Thread r1 = new Thread(new RaceCondition());
        Thread r2 = new Thread(new RaceCondition());
        r1.start();
        r2.start();
        while (r1.isAlive() || r2.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.raceCondition;
    }

    final class Visibility implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                visibility++;
            }
        }

    }

    final class RaceCondition implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                raceCondition++;
            }
        }

    }

}
