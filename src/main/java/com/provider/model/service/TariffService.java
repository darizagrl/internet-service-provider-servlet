package com.provider.model.service;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.exception.DAOException;
import com.provider.model.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class TariffService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private final Logger logger = LogManager.getLogger(TariffService.class);

    public List<Tariff> findPaginatedAndSorted(int serviceId, String sortField, String sortOrder, Integer currentPageNum, Integer recordsPerPage){
        try (TariffDao dao = daoFactory.getTariffDao()) {
            return dao.findPaginatedAndSorted(serviceId, sortField, sortOrder, currentPageNum, recordsPerPage);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }


    public List<Tariff> findAllByServiceId(int serviceId) {
        try (TariffDao dao = daoFactory.getTariffDao()) {
            logger.info("Finding tariffs with service id {}:", serviceId);
            return dao.findAllByServiceId(serviceId);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public List<Tariff> findAll() {
        try (TariffDao dao = daoFactory.getTariffDao()) {
            return dao.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void create(Tariff tariff) {
        try (TariffDao dao = daoFactory.getTariffDao()) {
            logger.info("Creating tariff with name: {}", tariff.getName());
            dao.create(tariff);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void delete(int tariffId) {
        try (TariffDao dao = daoFactory.getTariffDao()) {
            logger.info("Deleting tariff with id: {}", tariffId);
            dao.delete(tariffId);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public void update(Tariff tariff) {
        try (TariffDao dao = daoFactory.getTariffDao()) {
            logger.info("Updating tariff with name: {}", tariff.getName());
            dao.update(tariff);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    public Tariff findById(int id) {
        try (TariffDao dao = daoFactory.getTariffDao()) {
            logger.info("Searching for tariff with id: {}", id);
            return dao.findById(id);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new DAOException(e);
        }
    }

}
