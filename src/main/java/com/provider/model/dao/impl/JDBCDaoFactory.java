package com.provider.model.dao.impl;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/provider-servlet",
                    "root",
                    "root");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }

    @Override
    public UserDao getUserDao() throws SQLException, ClassNotFoundException {
        try {
            return new JDBCUserDao(getConnection());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }

    @Override
    public ServiceDao getServiceDao() throws SQLException, ClassNotFoundException {
        try {
            return new JDBCServiceDao(getConnection());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }

    @Override
    public TariffDao getTariffDao() throws SQLException, ClassNotFoundException {
        try {
            return new JDBCTariffDao(getConnection());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }
}