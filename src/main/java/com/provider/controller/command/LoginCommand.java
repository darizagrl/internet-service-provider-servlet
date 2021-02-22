package com.provider.controller.command;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws java.lang.Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        List<User> userList = dao.findAll();

        for (User user : userList) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                if (user.getRole().getId().equals(1)) {
                    CommandUtility.setUserRole(request, user.getRole(), email);
                    CommandUtility.setUserAsAttribute(request, user);
                    return "redirect:/admin/admin_index";
                } else if (user.getRole().getId().equals(2)) {
                    CommandUtility.setUserRole(request, user.getRole(), email);
                    CommandUtility.setUserAsAttribute(request, user);
                    return "redirect:/user/user_index";
                }
            }
        }
        request.setAttribute("message", "Wrong login or password.");
        return "/login.jsp";
    }
}