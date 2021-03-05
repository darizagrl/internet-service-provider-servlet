package com.provider.model.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    static {
        logger.info("Creating connection pool...");
        config.setJdbcUrl(resourceBundle.getString("db.url"));
        config.setUsername(resourceBundle.getString("db.user"));
        config.setPassword(resourceBundle.getString("db.password"));
        config.setDriverClassName(resourceBundle.getString("db.driver"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
        }
        return null;
    }
}
