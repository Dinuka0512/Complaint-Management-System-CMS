package com.example.jspcmsfinal.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cms");
        dataSource.setUsername("root");
        dataSource.setPassword("0512");

        // Pool settings
        dataSource.setInitialSize(5);        // Initial connections
        dataSource.setMinIdle(5);            // Minimum idle connections
        dataSource.setMaxIdle(10);           // Maximum idle connections
        dataSource.setMaxTotal(20);          // Total open connections
        dataSource.setMaxOpenPreparedStatements(100); // Optional
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Private constructor to prevent instantiation
    private DBConnectionPool() {}
}
