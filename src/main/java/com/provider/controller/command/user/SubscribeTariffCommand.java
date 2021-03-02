package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;
import com.provider.model.service.TariffService;
import com.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeTariffCommand implements Command {
    private final Logger logger = LogManager.getLogger(SubscribeTariffCommand.class);
    UserService userService;
    TariffService tariffService;

    public SubscribeTariffCommand(UserService userService, TariffService tariffService) {
        this.userService = userService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        Tariff tariff = tariffService.findById(tariffId);
        if (user.isBlocked()) {
            logger.warn("User {} is blocked", user.getEmail());
            request.setAttribute("message", "Your account is blocked. Top up your balance if you want to subscribe.");
            return "/user/user_index";
        }
        if (tariff.isConnectedToUser(user)) {
            logger.warn("User {} already has tariff {}", user.getEmail(), tariff.getName());
            request.setAttribute("message", "You are already subscribed to this tariff");
            return "/user/user_index";
        }
        if (tariff.getPrice() > (user.getBalance())) {
            user.setBlocked(true);
            logger.warn("The tariff {} was added to user account, but user {} was blocked", tariff.getName(), user.getEmail());
            request.setAttribute("message", "Insufficient funds! The tariff was added to your account. But would be able to use only after replenishment");
        } else {
            logger.info("The tariff {} was added to user {} account", tariff.getName(), user.getEmail());
            request.setAttribute("message", "You have successfully subscribed");
        }
        user.setBalance(user.getBalance() - tariff.getPrice());
        userService.subscribeTariff(user.getId(), tariffId);
        userService.update(user);
        logger.info("The tariff {} was added to user {} account", tariff.getName(), user.getEmail());
        return "/user/user_index";
    }
}
