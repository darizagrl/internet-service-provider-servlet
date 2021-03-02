package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountReplenishmentCommand implements Command {
    private final Logger logger = LogManager.getLogger(AccountReplenishmentCommand.class);
    UserService userService;

    public AccountReplenishmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = (User) request.getSession().getAttribute("user");
        logger.info("Replenishment of user {} account", currentUser.getEmail());
        double balance = 0.0;
        try {
            if (request.getParameter("balance") == null) {
                request.setAttribute("balance", balance);
                request.setAttribute("message", "Enter the amount of money to replenish");
                return "/user/account.jsp";
            } else {
                balance = Double.parseDouble(request.getParameter("balance"));
            }
        } catch (NumberFormatException e) {
            request.setAttribute("balance", 0.0);
            logger.error(e.getMessage());
            request.setAttribute("message", "Wrong number format");
            return "/user/account.jsp";
        }
        if (currentUser.isBlocked() && currentUser.getBalance() + balance >= 0) {
            currentUser.setBlocked(false);
            logger.info("Unlocking user {}", currentUser.getEmail());
        }
        if (balance < 0) {
            logger.warn("The number {} is less than 0", balance);
            request.setAttribute("message", "The number cannot be less than 0");
            return "/user/account.jsp";
        }
        if (balance > 3000) {
            logger.warn("The number {} is more than 3 000", balance);
            request.setAttribute("message", "You could only replenish your balance on 3000 in one transaction");
            return "/user/account.jsp";
        }
        currentUser.setBalance(currentUser.getBalance() + balance);
        userService.update(currentUser);
        logger.info("Replenishment of user {} account on {} was successful", currentUser.getEmail(), balance);
        return "/user/account";
    }
}
