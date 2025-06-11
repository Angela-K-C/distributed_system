package src.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/test_dbdrinks";
    private static final String USER = "root";
    private static final String PASSWORD = "asert";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
