package ru.nik66.tracker.start;

import ru.nik66.tracker.models.Bug;
import ru.nik66.tracker.models.Item;
import ru.nik66.tracker.models.Task;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, Closeable {

    private Connection connection;

    public boolean init() {
        try (InputStream is = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(is);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            checkTables();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private void checkTables() {
        try {
            DatabaseMetaData meta = this.connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "items", null);
            if (!tables.next()) {
                try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.CREATE.query)) {
                    statement.executeUpdate();
                }
            }
            tables.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.INSERT.query)) {
            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.setString(3, item.getDescription());
            statement.setLong(4, item.getCreate());
            statement.setString(5, this.checkItemType(item));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replace(Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.UPDATE.query)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setLong(3, item.getCreate());
            statement.setString(4, this.checkItemType(item));
            statement.setString(5, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.DELETE.query)) {
            statement.setString(1, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = null;
        try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.FIND_ALL.query)) {
            ResultSet rs = statement.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                if (rs.getString("type").equals("task")) {
                    result.add(new Task(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date")));
                } else if (rs.getString("type").equals("bug")) {
                    result.add(new Bug(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date")));
                } else {
                    result.add(new Item(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date")));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> result = null;
        try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.FIND_BY_NAME.query)) {
            name = String.format("%%%s%%", name);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                if (rs.getString("type").equals("task")) {
                    result.add(new Task(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date")));
                } else if (rs.getString("type").equals("bug")) {
                    result.add(new Bug(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date")));
                } else {
                    result.add(new Item(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement statement = this.connection.prepareStatement(SQLItems.FIND_BY_ID.query)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (rs.getString("type").equals("task")) {
                    result = new Task(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date"));
                } else if (rs.getString("type").equals("bug")) {
                    result = new Bug(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date"));
                } else {
                    result = new Item(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("create_date"));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String checkItemType(Item item) {
        String result = "item";
        if (item instanceof Task) {
            result = "task";
        }
        if (item instanceof Bug) {
            result = "bug";
        }
        return result;
    }

    enum SQLItems {
        CREATE("CREATE TABLE items (id CHARACTER VARYING (255) PRIMARY KEY, name CHARACTER VARYING (255), description TEXT, create_date BIGINT, type CHARACTER VARYING (100))"),
        INSERT("INSERT INTO items (id, name, description, create_date, type) VALUES ((?), (?), (?), (?), (?))"),
        UPDATE("UPDATE items SET name = (?), description = (?), create_date = (?), type = (?) WHERE id = (?)"),
        DELETE("DELETE FROM items AS i WHERE i.id = (?) RETURNING id"),
        FIND_ALL("SELECT id, name, description, create_date, type FROM items"),
        FIND_BY_NAME("SELECT id, name, description, create_date, type FROM items AS i WHERE i.name LIKE (?)"),
        FIND_BY_ID("SELECT id, name, description, create_date, type FROM items AS i WHERE i.id = (?)");

        String query;

        SQLItems(String query) {
            this.query = query;
        }
    }

}
