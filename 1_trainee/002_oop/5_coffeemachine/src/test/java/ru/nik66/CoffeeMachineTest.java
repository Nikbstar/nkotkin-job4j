package ru.nik66;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    public CoffeeMachine coffeeMachine;

    @Test
    public void whenValueFiftyAndPriceThirtyFiveThenReturnChangeCoinsTenAndFive() throws Exception {
        coffeeMachine = new CoffeeMachine();
        int[] actual = this.coffeeMachine.changes(50, 35);
        int[] expected = {10, 5};

        assertThat(actual, is(expected));
    }
}
