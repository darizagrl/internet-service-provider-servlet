package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnsubscribeCommand implements Command {
    private final Logger logger = LogManager.getLogger(UnsubscribeCommand.class);
    UserService userService;

    public UnsubscribeCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        userService.unsubscribeTariff(user.getId(), tariffId);
        logger.info("Tariff id={} was remove from user {}", tariffId, user.getEmail());
        request.setAttribute("message", "You have successfully unsubscribed");
        return "/user/account";
    }
}