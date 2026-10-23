package com.scc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:Database.db";

    public static Connection getConnection() throws SQLException {
        try {
            // Explicitly force the SQLite JDBC driver to load
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("CRITICAL: SQLite JDBC Driver jar is missing from IntelliJ Libraries! Please add it via File -> Project Structure -> Libraries.", e);
        }

        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    customer_name TEXT NOT NULL,
                    drink_item TEXT NOT NULL,
                    price REAL NOT NULL,
                    status TEXT NOT NULL
                );
            """);
        }
        return conn;
    }
}