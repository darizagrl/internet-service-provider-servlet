package com.provider.model.dao;

import com.provider.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {
    List<Tariff> findAllByServiceId(int serviceId);

    List<Tariff> findByServiceSortedASC(int serviceId);

    List<Tariff> findByServiceSortedDESC(int serviceId);

    List<Tariff> findByServiceSortedByPrice(int serviceId);
}
