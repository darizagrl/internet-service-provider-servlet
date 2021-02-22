package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UnsubscribeCommand implements Command {
    private final Logger logger = LogManager.getLogger(UnsubscribeCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            UserDao daoUser = factory.getUserDao();
            User user = (User) request.getSession().getAttribute("user");
            int tariffId = Integer.parseInt(request.getParameter("tariffId"));
            daoUser.unsubscribeTariff(user.getId(), tariffId);
            request.setAttribute("message", "You have successfully unsubscribed");
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
