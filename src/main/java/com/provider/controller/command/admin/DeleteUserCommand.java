package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteUserCommand implements Command {
    private final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
    UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   {
        int id = Integer.parseInt(request.getParameter("userId"));
        logger.info("Deleting user with id={}", id);
        userService.delete(id);
        logger.info("The user with id={} was deleted", id);
        List<User> userList = userService.findAll();
        request.setAttribute("userList", userList);
        return "/admin/user_management.jsp";
    }
}
