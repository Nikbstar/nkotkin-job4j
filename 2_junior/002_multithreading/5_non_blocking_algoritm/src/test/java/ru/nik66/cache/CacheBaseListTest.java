package ru.nik66.cache;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CacheBaseListTest {

    private CacheBaseList list;

    @Before
    public void init() {
        this.list = new CacheBaseList();
    }

    @Test
    public void whenAddNewModel() {
        boolean actual = this.list.add(new Base(1, 0));
        boolean expected = true;
        assertThat(actual, is(expected));
        assertThat(this.list.getValues().get(1).getVersion(), is(0));
    }

    @Test
    public void whenAddNotNewModel() {
        this.list.add(new Base(1, 0));
        this.list.getValues().get(1).setVersion(2);
        this.list.add(new Base(1, 0));
        int actual = this.list.getValues().get(1).getVersion();
        int expected = 2;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteModel() {
        this.list.add(new Base(1, 0));
        boolean actual = this.list.delete(new Base(1, 0));
        boolean expected = true;
        assertThat(actual, is(expected));
        assertThat(this.list.getValues().size(), is(0));
    }

    @Test (expected = OptimisticException.class)
    public void whenDeleteModelWithWrongVersion() {
        this.list.add(new Base(1, 0));
        this.list.delete(new Base(1, 1));
    }

    @Test
    public void whenUpdateModel() {
        this.list.add(new Base(1, 0));
        boolean actual = this.list.update(new Base(1, 0));
        boolean expected = true;
        assertThat(actual, is(expected));
        assertThat(this.list.getValues().get(1).getVersion(), is(1));
        this.list.update(new Base(1, 1));
        assertThat(this.list.getValues().get(1).getVersion(), is(2));
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateModelWithWrongVersion() {
        this.list.add(new Base(1, 0));
        this.list.update(new Base(1, 1));
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            try {
                throw new RuntimeException("Throw Exception in Thread");
            } catch (Exception e) {
                ex.set(e);
            }
        });
        thread.start();
        thread.join();
        String actual = ex.get().getMessage();
        String expected = "Throw Exception in Thread";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTwoThreadsTryToUpdateModel() throws InterruptedException {
        this.list.add(new Base(1, 0));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Runnable run = () -> {
            try {
                list.update(new Base(1, 0));
            } catch (OptimisticException oe) {
                ex.set(oe);
            }
        };
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(this.list.getValues().get(1).getVersion(), is(1));
        assertThat(ex.get().getMessage(), is("Can't update because version is wrong!"));
    }

}
