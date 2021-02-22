package com.provider.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private final DataSource dataSource;
    private static ConnectionPool instance = null;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");


    private ConnectionPool() {
        logger.info("Creating connection pool...");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(resourceBundle.getString("db.url"));
        basicDataSource.setUsername(resourceBundle.getString("db.user"));
        basicDataSource.setPassword(resourceBundle.getString("db.password"));
        basicDataSource.setDriverClassName(resourceBundle.getString("db.driver"));

        dataSource = basicDataSource;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            logger.info("Connection pool has been created.");
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
