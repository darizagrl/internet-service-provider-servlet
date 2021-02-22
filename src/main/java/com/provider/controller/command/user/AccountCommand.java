package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        DaoFactory factory = DaoFactory.getInstance();
        UserDao daoUser = factory.getUserDao();
        User user = (User) request.getSession().getAttribute("user");
        user = daoUser.findUserTariffs(user);
        request.getSession().setAttribute("user", user);
        return "/user/account.jsp";
    }
}
