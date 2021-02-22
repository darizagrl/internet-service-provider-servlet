package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserManagementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        List<User> userList = dao.findAll();
        request.setAttribute("userList", userList);
        return "/admin/user_management.jsp";
    }
}
