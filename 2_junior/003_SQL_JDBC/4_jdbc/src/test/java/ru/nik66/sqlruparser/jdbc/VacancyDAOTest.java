package ru.nik66.sqlruparser.jdbc;

import org.junit.Test;
import ru.nik66.sqlruparser.model.Vacancy;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class VacancyDAOTest {

    @Test
    public void whenCreteNewVacancyThenReturnItAndLastUpdateIsFromIt() throws Exception {
        File tempFile = File.createTempFile("vocancy", ".db");
        String url = "jdbc:sqlite:".concat(tempFile.getAbsolutePath());
        try (Connection connection = DriverManager.getConnection(url)) {
            Vacancy vacancy = new Vacancy("Вакансия", "ссылка", LocalDateTime.now());
            VacancyDAO dao = new VacancyDAO(connection);
            dao.create(vacancy);
            List<Vacancy> actual = dao.read();
            List<Vacancy> expected = Collections.singletonList(vacancy);
            assertThat(actual, is(expected));
            //assertThat(dao.getLastUpdate(), is(vacancy.getDate()));
        }
    }

}