package com.provider.model.dao.impl;

import com.provider.model.dao.UserDao;
import com.provider.model.entity.Role;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.provider.model.Constants.*;

public class JDBCUserDao implements UserDao {
    private final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("User creation has failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try (PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_USER_ROLE)) {
            preparedStatement2.setInt(1, user.getId());
            preparedStatement2.setInt(2, 2);
            Role role = new Role(2, "user");
            user.setRole(role);
            preparedStatement2.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                boolean isBlocked = resultSet.getBoolean(6);
                double balance = resultSet.getDouble(7);
                user = new User(firstname, lastname, email, password);
                user.setId(userId);
                user.setBalance(balance);
                user.setBlocked(isBlocked);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_AND_ROLES);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                boolean isBlocked = resultSet.getBoolean(6);
                double balance = resultSet.getDouble(7);
                Integer roleId = resultSet.getInt(8);
                String roleName = resultSet.getString(9);
                User user = new User(firstname, lastname, email, password);
                user.setId(id);
                user.setBalance(balance);
                user.setBlocked(isBlocked);
                Role role = new Role(roleId, roleName);
                user.setRole(role);
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.isBlocked());
            preparedStatement.setDouble(6, user.getBalance());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public User findUserTariffs(User user) {
        List<Tariff> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_TARIFFS)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int tariffId = resultSet.getInt(3);
                String name = resultSet.getString(4);
                String description = resultSet.getString(5);
                double price = resultSet.getDouble(6);
                int idService = resultSet.getInt(8);
                String serviceName = resultSet.getString(9);
                Service service = new Service(serviceName);
                service.setId(idService);
                Tariff tariff = new Tariff(name, description, price);
                tariff.setId(tariffId);
                tariff.setService(service);
                list.add(tariff);
            }
            user.setTariffs(list);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public void subscribeTariff(int userId, int tariffId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SUBSCRIBE_TARIFF, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tariffId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void unsubscribeTariff(int userId, int tariffId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UNSUBSCRIBE_TARIFF)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tariffId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
