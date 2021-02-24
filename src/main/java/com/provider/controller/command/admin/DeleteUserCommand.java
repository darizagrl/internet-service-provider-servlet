package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteUserCommand implements Command {
    UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   {
        int id = Integer.parseInt(request.getParameter("userId"));
        userService.delete(id);
        List<User> userList = userService.findAll();
        request.setAttribute("userList", userList);
        return "/admin/user_management.jsp";
    }
}
