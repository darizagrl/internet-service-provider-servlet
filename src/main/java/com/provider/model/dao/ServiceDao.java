package com.provider.model.dao;

import com.provider.model.entity.Service;

public interface ServiceDao extends GenericDao<Service> {
    Service findByName(String name);
}
