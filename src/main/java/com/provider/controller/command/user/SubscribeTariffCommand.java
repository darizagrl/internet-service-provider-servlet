package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SubscribeTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        DaoFactory factory = DaoFactory.getInstance();
        UserDao daoUser = factory.getUserDao();
        TariffDao daoTariff = factory.getTariffDao();
        User user = (User) request.getSession().getAttribute("user");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        Tariff tariff = daoTariff.findById(tariffId);
        if (user.isBlocked()) {
            request.setAttribute("message", "Your account is blocked. Top up your balance if you want to subscribe.");
        } else {
            if (tariff.getPrice() > (user.getBalance())) {
                user.setBlocked(true);
                request.setAttribute("message", "Insufficient funds! The tariff was added to your account. But would be able to use only after replenishment");
            }
            user.setBalance(user.getBalance() - tariff.getPrice());
            daoUser.subscribeTariff(user.getId(), tariffId);
            daoUser.update(user);
            request.setAttribute("message", "You have successfully subscribed");
        }
        return "/user/user_index";
    }
}
