package org.self.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect

{

    static Dotenv dotenv = Dotenv.load();

    static String dbHost = dotenv.get("DB_HOST");
    static String dbPort = dotenv.get("DB_PORT");
    static String dbName = dotenv.get("DB_NAME");
    static String userName = dotenv.get("DB_USER");
    static String password = dotenv.get("DB_PASSWORD");

    static String jdbcURL = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    private static Connection conn=null;
    static {
        try {
            conn = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    /**
     * Returns a connection to the database.
     *
     * @return a Connection object
     * @throws SQLException if a connection cannot be retrieved
     */
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(jdbcURL, userName, password);
        }
        return conn;
    }

    /**
     * Closes the database connection.
     */
    public static void Disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection: " + e.getMessage());
        }
    }
}