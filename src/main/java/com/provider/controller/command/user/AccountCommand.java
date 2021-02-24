package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountCommand implements Command {
    UserService userService;

    public AccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        user = userService.findUserTariffs(user);
        request.getSession().setAttribute("user", user);
        return "/user/account.jsp";
    }
}
