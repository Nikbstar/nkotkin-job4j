package ru.nik66.jdbcexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SQLStorage {

    private static final Logger log = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
        String username = "postgres";
        String password = "1";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            // SELECT
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users AS u WHERE u.id IN (?, ?)");
            st.setInt(1, 1);
            st.setInt(2, 3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%d %s", rs.getInt("id"), rs.getString("name")));
            }
            rs.close();
            st.close();
//            // INSERT DELETE UPDATE
//            PreparedStatement st = conn.prepareStatement("INSERT INTO users (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
//            st.setString(1, "JavaUser");
//            st.executeUpdate();
//            ResultSet generatedKeys = st.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                System.out.println(generatedKeys.getInt(1));
//            }
//            generatedKeys.close();
//            st.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }


    }

}
