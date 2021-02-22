package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SubscribeTariffCommand implements Command {
    private final Logger logger = LogManager.getLogger(SubscribeTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        try {
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
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }
}
