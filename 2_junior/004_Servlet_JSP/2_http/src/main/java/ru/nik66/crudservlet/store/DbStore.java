package ru.nik66.crudservlet.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nik66.crudservlet.model.Role;
import ru.nik66.crudservlet.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

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
            this.checkTable();
        } catch (IOException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void checkTable() {
        try (Connection connection = SOURCE.getConnection()) {
            ResultSet tables = connection.getMetaData().getTables(null, null, "users", null);
            if (!tables.next()) {
                try (PreparedStatement statement = connection.prepareStatement(SQLUsers.CREATE.query)) {
                    statement.executeUpdate();
                }
            }
            tables.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.INSERT.query)
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getEmail());
            statement.setTimestamp(6, Timestamp.valueOf(user.getCreateDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.UPDATE.query)
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.DELETE_ID.query)

        ) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = null;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.SELECT_ALL.query)
        ) {
            ResultSet rs = statement.executeQuery();
            result = new CopyOnWriteArrayList<>();
            while (rs.next()) {
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("email"),
                        rs.getTimestamp("date").toLocalDateTime())
                );
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.SELECT_ID.query)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("email"),
                        rs.getTimestamp("date").toLocalDateTime()
                );
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void clear() {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUsers.DELETE_ALL.query)
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    enum SQLUsers {
        CREATE("CREATE TABLE users (id INTEGER PRIMARY KEY, name VARCHAR(255), login VARCHAR(255), password VARCHAR(255), role VARCHAR(255), email VARCHAR(255), date DATETIME)"),
        INSERT("INSERT INTO users (name, login, password, role, email, date) VALUES ((?), (?), (?), (?), (?), (?))"),
        UPDATE("UPDATE users SET name = (?), login = (?), password = (?), role = (?), email = (?) WHERE id = (?)"),
        DELETE_ID("DELETE FROM users WHERE id = (?)"),
        SELECT_ALL("SELECT id, name, login, password, role, email, date FROM users"),
        SELECT_ID("SELECT id, name, login, password, role, email, date FROM users WHERE id = (?)"),
        DELETE_ALL("DELETE FROM users");

        String query;

        SQLUsers(String query) {
            this.query = query;
        }
    }

}
