package com.provider.controller.command;

import com.provider.model.entity.User;
import com.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegistrationCommand implements Command {
    UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Registration of a new user");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirmEmail");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        logger.info("User email:" + email);
        logger.info("User password:" + password);
        if (email == null || email.equals("") || password == null || password.equals("") ||
                confirmPassword == null || confirmPassword.equals("")) {
            logger.warn("Email or password is empty.");
            request.setAttribute("message", "You have empty fields.");
            return "/admin/registration.jsp";
        }
        if (!(password.equals(confirmPassword))) {
            logger.warn("The password does not match.");
            request.setAttribute("message", "The password does not match.");
            return "/admin/registration.jsp";
        }
        if (!(email.equals(confirmEmail))) {
            logger.warn("The email does not match.");
            request.setAttribute("message", "The email does not match.");
            return "/admin/registration.jsp";
        }
        if (email.length() < 4 || email.length() > 25) {
            logger.warn("The email length is out of range [4, 25] symbols, email= {}", email);
            request.setAttribute("message", "The email has to be between 4 and 25 symbols");
            return "/admin/registration.jsp";
        }
        if (password.length() < 1 || password.length() > 20) {
            logger.warn("The password length is out of range [1, 20] symbols");
            request.setAttribute("message", "The password has to be between 1 and 20 symbols");
            return "/admin/registration.jsp";
        }
        List<User> userList = userService.findAll();
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                logger.warn("The user with email {} already exist", email);
                request.setAttribute("message", "The user with this email already exist.");
                return "/admin/registration.jsp";
            }
        }
        User user = new User(firstname, lastname, email, password);
        userService.create(user);
        logger.info("The user with email {} was created", user.getEmail());
        return "redirect:/admin/user_management";
    }
//TODO password encoder
}