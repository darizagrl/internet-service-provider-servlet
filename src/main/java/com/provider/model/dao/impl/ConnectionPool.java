package com.provider.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionPool {
        private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        logger.info("Creating connection pool...");
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            logger.info("Connection pool has been created.");
        }
        return instance;
    }

    public Connection getConnection() {
        Context context;
        Connection c = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/provider-servlet");
            c = ds.getConnection();
        } catch (NamingException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return c;
    }
}
