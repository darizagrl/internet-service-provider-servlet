package com.provider.model.dao.impl;

import com.provider.model.dao.*;
import com.provider.model.dao.exception.DAOConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);

    private Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/provider-servlet",
                    "root",
                    "root");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOConfigurationException(e);
        }
    }

    @Override
    public UserDao getUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ServiceDao getServiceDao() {
        return new JDBCServiceDao(getConnection());
    }

    @Override
    public TariffDao getTariffDao() {
        return new JDBCTariffDao(getConnection());
    }
}