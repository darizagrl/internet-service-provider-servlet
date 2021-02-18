package com.provider.model.dao.impl;

import com.provider.model.dao.TariffDao;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTariffDao implements TariffDao {
    public static final String FIND_TARIFFS_BY_SERVICE = "SELECT * FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.service_id=?;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_ASC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.name;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_DESC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.name DESC;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_BY_PRICE_ASC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.price ASC;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_BY_PRICE_DESC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.price DESC";
    public static final String CREATE_TARIFF = "INSERT INTO tariff (name, description, price, service_id) VALUES (?,?,?,?)";
    public static final String FIND_TARIFF = "SELECT * FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.id=?;";
    public static final String FIND_ALL_TARIFFS = "Select * from tariff inner join service on tariff.service_id = service.id;";
    public static final String UPDATE_TARIFF = "UPDATE tariff SET name=?, description=?, price=?, service_id=? WHERE tariff.id=?;";
    public static final String DELETE_TARIFF = "DELETE FROM tariff WHERE tariff.id = ?;";

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
    public List<Tariff> findByServiceSortedASC(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE_SORTED_ASC)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Tariff> findByServiceSortedDESC(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE_SORTED_DESC)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Tariff> findByServiceSortedByPriceASC(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE_SORTED_BY_PRICE_ASC)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Tariff> findByServiceSortedByPriceDESC(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE_SORTED_BY_PRICE_DESC)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            logger.error(e.getMessage());
        }
        return list;
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
            logger.error(e.getLocalizedMessage());
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
    public void update(Tariff entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TARIFF)) {
            preparedStatement.setString(1, (entity.getName()));
            preparedStatement.setString(2, (entity.getDescription()));
            preparedStatement.setDouble(3, (entity.getPrice()));
            preparedStatement.setInt(4, (entity.getService().getId()));
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TARIFF)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void close() {
    }
}
