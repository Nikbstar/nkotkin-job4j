package ru.nik66.sqlruparser.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nik66.sqlruparser.model.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VacancyDAO {

    private static final Logger LOG = LoggerFactory.getLogger(VacancyDAO.class);

    private final Connection connection;

    public VacancyDAO(final Connection connection) {
        this.connection = connection;
        this.initTable();
    }

    private void initTable() {
        try {
            DatabaseMetaData meta = this.connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "vacancy", null);
            if (!tables.next()) {
                try (PreparedStatement statement = this.connection.prepareStatement(SQLVacancy.CREATE.query)) {
                    statement.executeUpdate();
                }
            }
            tables.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void create(final Vacancy model) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLVacancy.INSERT.query)) {
            statement.setString(1, model.getTitle());
            statement.setString(2, model.getLink());
            statement.setTimestamp(3, Timestamp.valueOf(model.getDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public List<Vacancy> read() {
        List<Vacancy> result = new ArrayList<>();
        String title;
        String link;
        LocalDateTime date;
        try (PreparedStatement statement = this.connection.prepareStatement(SQLVacancy.SELECT.query)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                title = rs.getString("title");
                link = rs.getString("link");
                date = rs.getTimestamp("date").toLocalDateTime();
                result.add(new Vacancy(title, link, date));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    public LocalDateTime getLastUpdate() {
        LocalDateTime result = null;
        try (PreparedStatement statement = this.connection.prepareStatement(SQLVacancy.LAST_DATE.query)) {
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getTimestamp("date").toLocalDateTime();
            } else {
                result = LocalDateTime.now().minusYears(1);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    enum SQLVacancy {

        INSERT("INSERT INTO vacancy (title, link, date) VALUES ((?), (?), (?))"),
        SELECT("SELECT title, link, date FROM vacancy ORDER BY date DESC"),
        LAST_DATE("SELECT date FROM vacancy ORDER BY date DESC LIMIT 1"),
        CREATE("CREATE TABLE vacancy (id INTEGER PRIMARY KEY, title VARCHAR(255), link VARCHAR(255), date DATETIME)");

        String query;

        SQLVacancy(String query) {
            this.query = query;
        }

    }

}
