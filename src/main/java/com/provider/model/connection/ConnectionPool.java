package com.provider.model.connection;

import com.provider.model.dao.exception.DAOConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;

    public static synchronized ConnectionPool getInstance() throws DAOConfigurationException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() throws DAOConfigurationException {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/postgres");
            logger.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            logger.error("Cannot obtain the data source", ex);
            throw new DAOConfigurationException("Cannot obtain the data source", ex);
        }
    }

    private DataSource ds;

    public Connection getConnection() throws DAOConfigurationException {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            logger.error("Cannot obtain connection", ex);
            throw new DAOConfigurationException("Cannot obtain connection", ex);
        }
        return con;
    }
}
