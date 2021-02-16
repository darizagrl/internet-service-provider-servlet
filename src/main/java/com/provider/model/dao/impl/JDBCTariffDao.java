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
    private final Logger logger = LogManager.getLogger(JDBCTariffDao.class);
    private final Connection connection;

    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Tariff> findAllByServiceId(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        String sql = "SELECT tariff.id, tariff.name, description, price, service_id, service.name FROM tariff INNER JOIN service on tariff.service_id = service.id WHERE tariff.service_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
            logger.error("Cannot find tariff");
        }
        return list;
    }

    @Override
    public List<Tariff> findByServiceSortedASC(int serviceId) {
        List<Tariff> list = new ArrayList<>();
        String sql = "SELECT tariff.id, tariff.name, description, price, service_id, service.name FROM tariff INNER JOIN service on tariff.service_id = service.id where tariff.service_id=? order by tariff.name;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int idService = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(idService);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(id);
                tariff.setService(service);
                list.add(tariff);
            }
        } catch (SQLException e) {
            logger.error("Cannot find user");
        }
        return list;
    }

    @Override
    public List<Tariff> findByServiceSortedDESC(int serviceId) {
        List<Tariff> list = new ArrayList<>();

        String sql = "SELECT tariff.id, tariff.name, description, price, service_id, service.name FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.service_id=? ORDER BY tariff.name DESC;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int idService = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(idService);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(id);
                tariff.setService(service);
                list.add(tariff);
            }
        } catch (SQLException e) {
            logger.error("Cannot find user");
        }
        return list;
    }

    @Override
    public List<Tariff> findByServiceSortedByPrice(int serviceId) {
        List<Tariff> list = new ArrayList<>();

        String sql = "SELECT tariff.id, tariff.name, description, price, service_id, service.name FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.service_id=? ORDER BY tariff.price;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int idService = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(idService);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(id);
                tariff.setService(service);
                list.add(tariff);
            }
        } catch (SQLException e) {
            logger.error("Cannot find user");
        }
        return list;
    }

    @Override
    public void create(Tariff tariff) {
        String sql = "INSERT INTO tariff (name, description, price, service_id) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
            logger.error(e.getMessage());
        }
    }

    @Override
    public Tariff findById(int id) {
        Tariff tariff = null;
        String sql = "SELECT tariff.id, tariff.name, description, price, service_id, service.name FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
            ResultSet resultSet = statement.executeQuery("SELECT tariff.id, tariff.name, description, price, service_id, service.name FROM tariff INNER JOIN service on tariff.service_id = service.id;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int idService = resultSet.getInt(6);
                String servName = resultSet.getString(7);
                Service service = new Service(servName);
                service.setId(idService);
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
    public void update(Tariff entity) {
        String sql = "UPDATE tariff SET name=?, description=?, price=?, service_id=? WHERE tariff.id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, (entity.getName()));
            preparedStatement.setString(2, (entity.getDescription()));
            preparedStatement.setDouble(3, (entity.getPrice()));
            preparedStatement.setInt(4, (entity.getService().getId()));
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tariff WHERE tariff.id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void close() {
    }
}
