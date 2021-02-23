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

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
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
            request.setAttribute("message", "You have empty fields.");
            return "/admin/registration.jsp";
        }
        if (!(password.equals(confirmPassword))) {
            request.setAttribute("message", "The password does not match.");
            return "/admin/registration.jsp";
        }
        if (!(email.equals(confirmEmail))) {
            request.setAttribute("message", "The email does not match.");
            return "/admin/registration.jsp";
        }
        if (email.length() < 4 || email.length() > 20 || password.length() < 4 || password.length() > 20) {
            request.setAttribute("message", "Minimum length of login and password = 4.\n"
                    + "Maximum length of login and password = 20");
            return "/admin/registration.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        List<User> userList = dao.findAll();
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                request.setAttribute("message", "This email already exist.");
                return "/admin/registration.jsp";
            }
        }
        User user = new User(firstname, lastname, email, password);
        dao.create(user);
        return "redirect:/admin/user_management";
    }
//TODO password encoder
}