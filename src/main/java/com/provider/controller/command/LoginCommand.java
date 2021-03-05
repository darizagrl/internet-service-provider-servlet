package com.provider.controller.command;

import com.provider.model.entity.User;
import com.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginCommand implements Command {
    UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }
        if (CommandUtility.checkUserLogged(request, email)) {
            return "/WEB-INF/error.jsp";
        }
        List<User> userList = userService.findAll();
        logger.info("User email:" + email);
        logger.info("User password:" + password);
        for (User user : userList) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                if (user.getRole().getId().equals(1)) {
                    CommandUtility.setUserRole(request, user.getRole(), email);
                    CommandUtility.setUserAsAttribute(request, user);
                    logger.info("User role:" + user.getRole());
                    return "redirect:/admin/admin_index";
                } else if (user.getRole().getId().equals(2)) {
                    CommandUtility.setUserRole(request, user.getRole(), email);
                    CommandUtility.setUserAsAttribute(request, user);
                    logger.info("User role:" + user.getRole());
                    return "redirect:/user/user_index";
                }
            }
        }
        request.setAttribute("message", "Wrong login or password.");
        return "/login.jsp";
    }
}