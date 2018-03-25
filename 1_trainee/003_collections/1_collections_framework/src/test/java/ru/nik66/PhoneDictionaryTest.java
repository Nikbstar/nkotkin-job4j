package ru.nik66;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PhoneDictionaryTest {

    PhoneDictionary phones;
    Person nikolay;

    @Before
    public void initialization() {
        phones = new PhoneDictionary();
        nikolay = new Person("Nikolay", "Kotkin", "888958", "Yekaterinburg");
        phones.add(nikolay);
    }

    @Test
    public void whenFindByName() {
        List<Person> actual = phones.find("Nik");
        List<Person> expected = new ArrayList<>();
        expected.add(nikolay);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindBySurname() {
        List<Person> actual = phones.find("kin");
        List<Person> expected = new ArrayList<>();
        expected.add(nikolay);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindByPhone() {
        List<Person> actual = phones.find("888");
        List<Person> expected = new ArrayList<>();
        expected.add(nikolay);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFindByAddress() {
        List<Person> actual = phones.find("Yekaterinburg");
        List<Person> expected = new ArrayList<>();
        expected.add(nikolay);
        assertThat(actual, is(expected));
    }

}
