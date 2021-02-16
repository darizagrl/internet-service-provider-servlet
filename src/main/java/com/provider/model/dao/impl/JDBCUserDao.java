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

public class JDBCUserDao implements UserDao {
    private final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        String sql = "Insert into users (firstname,lastname, email ,password, isblocked, balance) Values (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.isBlocked());
            preparedStatement.setDouble(6, user.getBalance());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Cannot create user");
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        String sql = "SELECT users.id, firstname, lastname, email, password, isblocked, balance, name\n" +
                "\tFROM public.users, public.users_roles\n" +
                "\tinner join public.role on public.users_roles.role_id=public.role.id\n" +
                "\tWHERE users.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
                String role = resultSet.getString(8);
                user = new User(firstname, lastname, email, password, role);
                user.setId(userId);
                user.setBalance(balance);
                user.setBlocked(isBlocked);
            }
        } catch (SQLException e) {
            logger.error("Cannot find user");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ORDER BY id;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                boolean isBlocked = resultSet.getBoolean(6);
                double balance = resultSet.getDouble(7);
                String role = "admin";
                User user = new User(firstname, lastname, email, password, role);
                user.setId(id);
                user.setBalance(balance);
                user.setBlocked(isBlocked);
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("Cannot find users");
        }
        return list;
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE users SET firstname=?,lastname=?, email=?,password=?, isblocked=?,balance=? WHERE users.id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getFirstname());
            preparedStatement.setString(2, entity.getLastname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setBoolean(5, entity.isBlocked());
            preparedStatement.setDouble(6, entity.getBalance());
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot update user");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE users.id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete user");
        }
    }

    @Override
    public void close() {
    }

    @Override
    public User findUserTariffs(User user) {
        List<Tariff> list = new ArrayList<>();
        String sql = "SELECT * FROM users_tariffs INNER JOIN tariff ON users_tariffs.tariff_id = tariff.id=tarifF.id\r\n" +
                " inner join service on tariff.service_id= service.id where user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
            logger.error("Cannot find user's tariffs");
        }
        return user;
    }

    @Override
    public void insertUserTariffs(int userId, int tariffId) {
        String sql = "INSERT INTO users_tariffs (user_id, tariff_id) VALUES (?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tariffId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot insert tariff to user");
        }
    }

    @Override
    public void removeUserTariffs(int userId, int tariffId) {
        String sql = "DELETE FROM users_tariffs where user_id = ? AND tariff_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tariffId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot remove tariff from user");
        }
    }
}
