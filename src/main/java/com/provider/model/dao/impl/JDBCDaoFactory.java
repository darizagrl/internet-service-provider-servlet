package com.provider.model.dao.impl;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/provider-servlet",
                    "root",
                    "root");
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    public UserDao createUserDao() throws SQLException, ClassNotFoundException {
        try {
            return new JDBCUserDao(getConnection());
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    public ServiceDao createServiceDao() throws SQLException, ClassNotFoundException {
        try {
            return new JDBCServiceDao(getConnection());
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    public TariffDao createTariffDao() throws SQLException, ClassNotFoundException {
        try {
            return new JDBCTariffDao(getConnection());
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }
}