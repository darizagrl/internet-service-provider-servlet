package com.provider.model.dao;

import com.provider.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findUserTariffs(User user);

    void insertUserTariffs(int userId, int tariffId);

    void removeUserTariffs(int userId, int tariffId);
}
