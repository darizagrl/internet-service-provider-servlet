package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;
import com.provider.model.service.TariffService;
import com.provider.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeTariffCommand implements Command {
    UserService userService;
    TariffService tariffService;

    public SubscribeTariffCommand(UserService userService, TariffService tariffService) {
        this.userService = userService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        Tariff tariff = tariffService.findById(tariffId);
        if (user.isBlocked()) {
            request.setAttribute("message", "Your account is blocked. Top up your balance if you want to subscribe.");
        } else {
            if (tariff.getPrice() > (user.getBalance())) {
                user.setBlocked(true);
                request.setAttribute("message", "Insufficient funds! The tariff was added to your account. But would be able to use only after replenishment");
            }
            user.setBalance(user.getBalance() - tariff.getPrice());
            userService.subscribeTariff(user.getId(), tariffId);
            userService.update(user);
            request.setAttribute("message", "You have successfully subscribed");
        }
        return "/user/user_index";
    }
}
