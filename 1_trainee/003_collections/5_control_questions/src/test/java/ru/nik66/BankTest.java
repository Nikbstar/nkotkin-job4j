package ru.nik66;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.Account;
import ru.nik66.Bank;
import ru.nik66.User;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BankTest {
    private Bank bank;
    private User user1;
    private User user2;
    private Account acc1;
    private Account acc2;
    private Account acc3;

    @Before
    public void initialize() {
        this.bank = new Bank();
        this.user1 = new User("Kolya", "aaa111");
        this.user2 = new User("Katya", "bbb222");
        this.bank.addUser(this.user1);
        this.bank.addUser(this.user2);
        this.acc1 = new Account(100d, "first");
        this.acc2 = new Account(0d, "second");
        this.acc3 = new Account(100d, "third");
        this.bank.addAccountToUser("aaa111", this.acc1);
        this.bank.addAccountToUser("aaa111", this.acc2);
        this.bank.addAccountToUser("bbb222", this.acc3);
    }

    @Test
    public void whenTransferMoneyFromAccToAccOneUserThenTrue() {
        boolean actual = bank.transferMoney("aaa111", "first",
                "aaa111", "second", 20d);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferMoneyFormAccToAccOneUserThenFromAccMinus() {
        bank.transferMoney("aaa111", "first",
                "aaa111", "second", 20d);
        double actual = this.acc1.getValue();
        double expected = 80d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferMoneyFormAccToAccOneUserThenToAccPlus() {
        bank.transferMoney("aaa111", "first",
                "aaa111", "second", 20d);
        double actual = this.acc2.getValue();
        double expected = 20d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferMoneyFromAccToAccAnotherUserThenTrue() {
        boolean actual = bank.transferMoney("aaa111", "first",
                "bbb222", "third", 20d);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferMoneyFromAccToNullAccThenFalse() {
        boolean actual = bank.transferMoney("aaa111", "first",
                "bbb222", "first", 20d);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferMoneyFromAccToNullAccThenMoneyAreNotMinus() {
        bank.transferMoney("aaa111", "first",
                "bbb222", "first", 20d);
        double actual = acc1.getValue();
        double expected = 100d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNoMoneyToTransferThenFalse() {
        boolean actual = bank.transferMoney("aaa111", "first",
                "bbb222", "third", 120d);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNoMoneyToTransferThenFromAccAteNotMinus() {
        bank.transferMoney("aaa111", "first",
                "bbb222", "third", 120d);
        double actual = this.acc1.getValue();
        double expected = 100d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNoMoneyToTransferThenFromAccAteNotPlus() {
        bank.transferMoney("aaa111", "first",
                "bbb222", "third", 120d);
        double actual = this.acc3.getValue();
        double expected = 100d;
        assertThat(actual, is(expected));
    }
}
