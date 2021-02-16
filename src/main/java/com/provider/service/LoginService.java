package com.provider.service;

import com.provider.model.dao.DaoFactory;
import com.provider.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class LoginService {
    public List<User> checkUserInDb(User user) throws SQLException, ClassNotFoundException {
        return DaoFactory.getInstance()
                .createUserDao()
                .findAll();
    }
}
