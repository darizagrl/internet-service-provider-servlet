package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UnsubscribeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao daoUser = factory.getUserDao();
        User user = (User) request.getSession().getAttribute("user");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        daoUser.unsubscribeTariff(user.getId(), tariffId);
        request.setAttribute("message", "You have successfully unsubscribed");
        return "/user/account";
    }
}