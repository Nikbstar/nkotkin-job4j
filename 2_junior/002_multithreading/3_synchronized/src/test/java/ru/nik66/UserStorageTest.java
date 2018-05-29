package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    private UserStorage storage;
    private User first;
    private User second;

    @Before
    public void initialize() {
        this.storage = new UserStorage();
        this.first = new User(1, 100);
        this.second = new User(2, 200);
        this.storage.add(this.first);
        this.storage.add(this.second);
    }

    @Test
    public void whenTransferFromFirstToSecondUser() {
        this.storage.transfer(1, 2, 50);
        this.storage.transfer(2, 1, 100);
        assertThat(this.first.getAmount(), is(150));
        assertThat(this.second.getAmount(), is(150));
    }

    @Test
    public void whenThreadTransfer() throws InterruptedException {
        Thread firstAmount = new ThreadUserAmount(1, 2, 50);
        Thread secondAmount = new ThreadUserAmount(2, 1, 100);
        firstAmount.start();
        secondAmount.start();
        firstAmount.join();
        secondAmount.join();
        assertThat(this.first.getAmount(), is(150));
        assertThat(this.second.getAmount(), is(150));
    }

    @Test
    public void whenUpdateAndRemove() {
        this.storage.update(new User(2, 500));
        assertThat(this.storage.getUsers().get(2).getAmount(), is(500));
        boolean actual = this.storage.delete(new User(5, 100));
        boolean expected = false;
        assertThat(actual, is(expected));
        actual = this.storage.delete(this.first);
        expected = true;
        assertThat(actual, is(expected));
    }

    private class ThreadUserAmount extends Thread {

        final int from;
        final int to;
        final int amount;

        ThreadUserAmount(int from, int to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public void run() {
            storage.transfer(this.from, this.to, this.amount);
        }

    }

}
