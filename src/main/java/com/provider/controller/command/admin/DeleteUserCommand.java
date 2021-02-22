package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.controller.command.ExceptionCommand;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DeleteUserCommand implements Command {
    private final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            UserDao dao = factory.getUserDao();
            int id = Integer.parseInt(request.getParameter("userId"));
            dao.delete(id);
            List<User> userList = dao.findAll();
            request.setAttribute("userList", userList);
            return "/admin/user_management.jsp";
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }
}
