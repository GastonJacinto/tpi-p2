package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/tfi-p2?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "gasti2000";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
