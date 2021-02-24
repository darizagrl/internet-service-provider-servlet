package com.provider.model.dao.impl;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;
import com.provider.model.dao.exception.DAOConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);

    public static Connection getConnection() {
        try {
            InputStream inputStream;
            Properties prop = new Properties();
            String propFileName = "db.properties";
            inputStream = JDBCDaoFactory.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' has not be found in the classpath");
            }
            return DriverManager.getConnection(prop.getProperty("db.url"),
                    prop.getProperty("db.user"),
                    prop.getProperty("db.password"));
        } catch (SQLException | IOException e) {
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