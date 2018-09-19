package ru.nik66.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

public class ArrayDuplicateTest {

    @Test
    public void whenAddArrayWithDuplicatesThenRemoveDuplicates() throws Exception {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] array = {"Привет", "Мир", "Привет", "Супер", "Мир"};

        String[] actual = ad.remove(array);
        String[] expected = {"Привет", "Мир", "Супер"};

        assertThat(actual, arrayContainingInAnyOrder(expected));
    }

    @Test
    public void whenAddAllHelloArrayThenReturnOneHello() throws Exception {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] array = {"Hello", "Hello", "Hello", "Hello", "Hello"};

        String[] actual = ad.remove(array);
        String[] expected = {"Hello"};

        assertThat(actual, arrayContainingInAnyOrder(expected));
    }

}
