package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnsubscribeCommand implements Command {
    UserService userService;

    public UnsubscribeCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        userService.unsubscribeTariff(user.getId(), tariffId);
        request.setAttribute("message", "You have successfully unsubscribed");
        return "/user/account";
    }
}