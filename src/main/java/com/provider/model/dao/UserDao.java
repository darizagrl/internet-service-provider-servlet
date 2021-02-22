package com.provider.model.dao;

import com.provider.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);

    User findUserTariffs(User user);

    void subscribeTariff(int userId, int tariffId);

    void unsubscribeTariff(int userId, int tariffId);
}
