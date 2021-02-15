package com.provider.model.dao.impl;

import com.provider.constants.SqlQueries;
import com.provider.model.dao.ServiceDao;
import com.provider.model.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceDao implements ServiceDao {
    private final Logger logger = LogManager.getLogger(JDBCServiceDao.class);
    private final Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Service entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.CREATE_SERVICE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating service failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("service error");
        }

    }

    @Override
    public Service findById(int id) {
        Service service = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.FIND_SERVICE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int servId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                service = new Service(name);
                service.setId(servId);
            }
        } catch (SQLException e) {
            logger.error("service error");
        }
        return service;
    }

    @Override
    public List<Service> findAll() {
        List<Service> list = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQueries.GET_ALL_SERVICE);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Service service = new Service(name);
                service.setId(id);
                list.add(service);
            }
        } catch (SQLException e) {
            logger.error("service error");
        }
        return list;
    }

    @Override
    public void update(Service entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.UPDATE_SERVICE_NAME)) {
            preparedStatement.setString(1, (entity.getName()));
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("service error");
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.DELETE_SERVICE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("service error");
        }
    }

    @Override
    public void close() {
    }
}
