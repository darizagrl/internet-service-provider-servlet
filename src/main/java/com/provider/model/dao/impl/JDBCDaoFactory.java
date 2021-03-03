package com.provider.model.dao.impl;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao getUserDao() {
        return new JDBCUserDao(ConnectionPool.getConnection());
    }

    @Override
    public ServiceDao getServiceDao() {
        return new JDBCServiceDao(ConnectionPool.getConnection());
    }

    @Override
    public TariffDao getTariffDao() {
        return new JDBCTariffDao(ConnectionPool.getConnection());
    }
}