package ru.nik66.pool;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {

    private ThreadPool pool;

    @Before
    public void init() {
        this.pool = new ThreadPool();
    }

    @Test
    public void whenThreadPool() throws InterruptedException {
        List<Integer> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 4; i++) {
            final int j = i;
            this.pool.work(() -> actual.add(j));
        }
        Thread.sleep(100);
        this.pool.shutdown();
        Collections.sort(actual);
        List<Integer> excepted = Arrays.asList(0, 1, 2, 3);
        assertThat(actual, is(excepted));
    }

    @Test (expected = RejectedExecutionException.class)
    public void whenShutdownAndRunPoolThenThrowsException() {
        this.pool.shutdown();
        this.pool.work(() -> System.out.println(1));
    }

}
