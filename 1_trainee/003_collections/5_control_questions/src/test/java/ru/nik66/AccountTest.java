package ru.nik66;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.Account;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountTest {

    Account accFrom;
    Account accTo;

    @Before
    public void initialize() {
        this.accFrom = new Account(100d, "111");
        this.accTo = new Account(0d, "222");
    }

    @Test
    public void whenTransferToThenTrue() {
        boolean actual = this.accFrom.transferTo(this.accTo, 20d);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferToThenValueAccFromIsMinus() {
        this.accFrom.transferTo(this.accTo, 20d);
        double actual = this.accFrom.getValue();
        double expected = 80d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTransferToThenValueAccToIsPlus() {
        this.accFrom.transferTo(this.accTo, 20d);
        double actual = this.accTo.getValue();
        double expected = 20d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNoMoneyToTransferThenFalse() {
        boolean actual = this.accFrom.transferTo(this.accTo, 120d);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNoMoneyThenValueAccFromIsNotChange() {
        this.accFrom.transferTo(this.accTo, 120d);
        double actual = this.accFrom.getValue();
        double expected = 100d;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenNoMoneyThenValueAccToIsNotChange() {
        this.accFrom.transferTo(this.accTo, 120d);
        double actual = this.accTo.getValue();
        double expected = 0d;
        assertThat(actual, is(expected));
    }

}
