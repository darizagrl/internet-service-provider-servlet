package com.provider.model.dao.impl;

import com.provider.model.dao.TariffDao;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.provider.model.Constants.*;

public class JDBCTariffDao implements TariffDao {
    private final Logger logger = LogManager.getLogger(JDBCTariffDao.class);
    private final Connection connection;

    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Tariff> findAllByServiceId(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int idService = resultSet.getInt(6);
                String serviceName = resultSet.getString(7);
                Service service = new Service(serviceName);
                service.setId(idService);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(id);
                tariff.setService(service);
                list.add(tariff);
            }
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<Tariff> findPaginatedAndSorted(int serviceId, String sortField, String sortOrder, Integer currentPageNum, Integer recordsPerPage) {
        List<Tariff> tariffs = new ArrayList<>();
        ResultSet resultSet;
        try (PreparedStatement stmt = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE + ORDER_BY + sortField + " " + sortOrder + LIMIT_OFFSET)) {
            stmt.setInt(1, serviceId);
            stmt.setInt(2, recordsPerPage);
            stmt.setInt(3, (currentPageNum - 1) * recordsPerPage);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int servId = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(servId);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(id);
                tariff.setService(service);
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return tariffs;
    }


    @Override
    public void create(Tariff tariff) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TARIFF, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tariff.getName());
            preparedStatement.setString(2, tariff.getDescription());
            preparedStatement.setDouble(3, tariff.getPrice());
            preparedStatement.setInt(4, tariff.getService().getId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tariff.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Tariff creation has failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public Tariff findById(int id) {
        Tariff tariff = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TARIFF)) {
            logger.info("Searching for tariff id={}", id);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int tarifId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int idService = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(idService);
                tariff = new Tariff(name, description, price);
                tariff.setId(tarifId);
                tariff.setService(service);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return tariff;
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> list = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TARIFFS);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int servId = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(servId);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(id);
                tariff.setService(service);
                list.add(tariff);
            }
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public void update(Tariff tariff) {
        logger.info("Updating tariff {}", tariff.getName());
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TARIFF)) {
            preparedStatement.setString(1, (tariff.getName()));
            preparedStatement.setString(2, (tariff.getDescription()));
            preparedStatement.setDouble(3, (tariff.getPrice()));
            preparedStatement.setInt(4, (tariff.getService().getId()));
            preparedStatement.setInt(5, tariff.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) {
        logger.warn("Deleting tariff id={}", id);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TARIFF)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.warn("Cannot close the connection");
        }
    }
}
