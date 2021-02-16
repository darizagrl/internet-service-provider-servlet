package com.provider.model.dao;

import com.provider.model.dao.impl.JDBCDaoFactory;

import java.sql.SQLException;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao() throws SQLException, ClassNotFoundException;

    public abstract ServiceDao createServiceDao() throws SQLException, ClassNotFoundException;

    public abstract TariffDao createTariffDao() throws SQLException, ClassNotFoundException;
}
