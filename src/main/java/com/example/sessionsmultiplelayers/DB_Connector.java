package com.example.sessionsmultiplelayers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
    private static String URL = "jdbc:mysql://localhost:3306/multisessions_db";
    private static String USER = "root";
    private static String PASS = "root";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) connection = DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

