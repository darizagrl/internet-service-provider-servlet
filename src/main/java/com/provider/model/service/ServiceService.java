package com.provider.model.service;

import com.provider.model.dao.exception.DAOException;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ServiceService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private final Logger logger = LogManager.getLogger(ServiceService.class);

    public List<Service> findAll() {
        try (ServiceDao dao = daoFactory.getServiceDao()) {
            return dao.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void create(Service service) {
        try (ServiceDao dao = daoFactory.getServiceDao()) {
            logger.info("Creating service with name: {}", service.getName());
            dao.create(service);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public Service findById(int id) {
        try (ServiceDao dao = daoFactory.getServiceDao()) {
            logger.info("Searching for service with id: {}", id);
            return dao.findById(id);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }
}