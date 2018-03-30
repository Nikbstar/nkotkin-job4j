package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCompareTest {

    ListCompare compare;

    @Before
    public void init() {
        this.compare = new ListCompare();
    }

    @Test
    public void whenStringAreEqualThenZero() {
        int actual = this.compare.compare("Ivanov", "Ivanov");
        int expected = 0;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        int actual = this.compare.compare("Ivanov", "Ivanova");
        int expected = 0;
        assertThat(actual, lessThan(expected));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        int actual = this.compare.compare("Petrov", "Ivanova");
        int expected = 0;
        assertThat(actual, greaterThan(expected));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        int actual = this.compare.compare("Petrov", "Patrov");
        int expected = 0;
        assertThat(actual, greaterThan(expected));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        int actual = this.compare.compare("Patrova", "Petrov");
        int expected = 0;
        assertThat(actual, lessThan(expected));
    }

}
