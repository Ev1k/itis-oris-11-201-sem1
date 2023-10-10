package com.derezhenko.net.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {
    private static Connection connection;
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String USER = "jdbc:postgresql://localhost:5432/postgres";
    public static final String PASSWORD = "rfrfirf12";
    public static final String URL = "postgres";


    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                java.sql.Connection connection =  DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                        );
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
