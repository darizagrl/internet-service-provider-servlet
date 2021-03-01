package com.provider.model.dao;

import com.provider.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {
    List<Tariff> findAllByServiceId(int serviceId);

    List<Tariff> findPaginatedAndSorted(int serviceId, String sortField, String sortOrder, Integer currentPageNum, Integer recordsPerPage);
}