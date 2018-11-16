package ru.nik66.crudservlet.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nik66.crudservlet.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DbStore implements Store {

    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);

    private static final String PROPERTIES = "app.properties";

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        try (InputStream is = DbStore.class.getClassLoader().getResourceAsStream(PROPERTIES)) {
            Properties config = new Properties();
            config.load(is);
            Class.forName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (IOException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.INSERT.query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void clear() {

    }

    enum SQLUsers {
        INSERT(""),
        UPDATE("");

        String query;

        SQLUsers(String query) {
            this.query = query;
        }

    }

}
