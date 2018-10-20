package ru.nik66.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StoreSQL implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);

    private final Properties config;

    private Connection connection;

    public StoreSQL(Properties config) {
        this.config = config;
    }

    public boolean init() {
        try {
            Class.forName(this.config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(this.config.getProperty("url"));
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        checkTables();
        return this.connection != null;
    }

    private void checkTables() {
        try {
            DatabaseMetaData meta = this.connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "entry", null);
            if (!tables.next()) {
                try (PreparedStatement statement = this.connection.prepareStatement(SQLEntry.CREATE.query)) {
                    statement.executeUpdate();
                }
            }
            tables.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    private void cleanTable() {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLEntry.CLEAN.query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generate(int n) {
        cleanTable();
        try (PreparedStatement statement = this.connection.prepareStatement(SQLEntry.INSERT.query)) {
            this.connection.setAutoCommit(false);
            for (int i = 1; i <= n; i++) {
                statement.setInt(1, i);
                statement.executeUpdate();
            }
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    public List<Entry> getEntryList() {
        List<Entry> result = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SQLEntry.SELECT.query)) {
            this.connection.setAutoCommit(false);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new Entry(rs.getInt("field")));
            }
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    enum SQLEntry {
        CREATE("CREATE TABLE entry (field INTEGER PRIMARY KEY)"),
        CLEAN("DELETE FROM entry"),
        INSERT("INSERT INTO entry (field) VALUES (?)"),
        SELECT("SELECT field FROM entry");

        String query;

        SQLEntry(String query) {
            this.query = query;
        }
    }
}
