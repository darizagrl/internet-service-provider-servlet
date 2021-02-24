package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.User;
import com.provider.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountReplenishmentCommand implements Command {
    UserService userService;

    public AccountReplenishmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = (User) request.getSession().getAttribute("user");
        double balance;
        try {
            if (request.getParameter("balance") == null) {
                balance = currentUser.getBalance();
                request.setAttribute("balance", balance);
                request.setAttribute("message", "Enter the amount of money to replenish");
                return "/user/account.jsp";
            } else {
                balance = Double.parseDouble(request.getParameter("balance"));
            }
        } catch (NumberFormatException e) {
            request.setAttribute("balance", 0.0);
            request.setAttribute("message", "Wrong number format");
            return "/user/account.jsp";
        }
        if (currentUser.isBlocked() && currentUser.getBalance() >= 0) {
            currentUser.setBlocked(false);
        }
        if (balance < 0) {
            request.setAttribute("message", "The number cannot be less than 0");
            return "/user/account.jsp";
        }
        if (balance > 3000) {
            request.setAttribute("message", "You could only replenish your balance on 3000 in one transaction");
            return "/user/account.jsp";
        }
        currentUser.setBalance(currentUser.getBalance() + balance);
        userService.update(currentUser);
        return "/user/account";
    }
}
