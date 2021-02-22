package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AccountReplenishmentCommand implements Command {
    private final Logger logger = LogManager.getLogger(AccountReplenishmentCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            UserDao dao = factory.getUserDao();
            User currentUser = (User) request.getSession().getAttribute("user");
            double balance;
            try {
                if (request.getParameter("balance") == null) {
                    balance = currentUser.getBalance();
                    request.setAttribute("balance", balance);
                    request.setAttribute("message", "Enter the amount of money to replenish");
                    return "/user/admin_account.jsp";
                } else {
                    balance = Double.parseDouble(request.getParameter("balance"));
                }
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                request.setAttribute("balance", 0.0);
                request.setAttribute("message", "Wrong number format");
                return "/user/admin_account.jsp";
            }
            if (currentUser.isBlocked() && currentUser.getBalance() >= 0) {
                currentUser.setBlocked(false);
            }
            if (balance < 0) {
                request.setAttribute("message", "The number cannot be less than 0");
                return "/user/admin_account.jsp";
            }
            if (balance > 3000) {
                request.setAttribute("message", "You could only replenish your balance on 3000 in one transaction");
                return "/user/admin_account.jsp";
            }
            currentUser.setBalance(currentUser.getBalance() + balance);
            dao.update(currentUser);
            return "/user/account";
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }
}
