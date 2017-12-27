package ru.nik66;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DoctorTest {

    @Test
    public void whenThen() throws Exception {
        Pacient pacient = new Pacient("Sergey");
        Doctor doctor = new Doctor("Ivan", 33, "No");

        String actual = doctor.heal(pacient).getDiagnos();
        String expected = "Doctor Ivan healing Sergey";

        assertThat(actual, is(expected));
    }

}
