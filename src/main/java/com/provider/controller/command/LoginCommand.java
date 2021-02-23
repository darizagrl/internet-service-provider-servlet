package com.provider.controller.command;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        List<User> userList = dao.findAll();
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