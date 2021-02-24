package com.provider.model.service;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.dao.exception.DAOException;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private final Logger logger = LogManager.getLogger(UserService.class);

    public void create(User user) {
        try (UserDao dao = daoFactory.getUserDao()) {
            dao.create(user);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void delete(int id) {
        try (UserDao dao = daoFactory.getUserDao()) {
            logger.info("Deleting tariff with id: {}", id);
            dao.delete(id);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public List<User> findAll() {
        try (UserDao dao = daoFactory.getUserDao()) {
            return dao.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public User findUserTariffs(User user) {
        try (UserDao dao = daoFactory.getUserDao()) {
            return dao.findUserTariffs(user);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void update(User user) {
        try (UserDao dao = daoFactory.getUserDao()) {
            dao.update(user);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void subscribeTariff(int userId, int tariffId) {
        try (UserDao dao = daoFactory.getUserDao()) {
            dao.subscribeTariff(userId, tariffId);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void unsubscribeTariff(int userId, int tariffId) {
        try (UserDao dao = daoFactory.getUserDao()) {
            dao.unsubscribeTariff(userId, tariffId);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }
}
