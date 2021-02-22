package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.entity.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CreateNewServiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String name = request.getParameter("name");
        if (name == null || name.equals("")) {
            return "/admin/service_add.jsp";
        }
        DaoFactory factory = DaoFactory.getInstance();
        ServiceDao dao = factory.getServiceDao();
        List<Service> list = dao.findAll();
        for (Service service : list) {
            if (name.equals(service.getName())) {
                request.setAttribute("message", "The service with this name already exist.");
                return "/admin/service_add.jsp";
            }
        }
        Service service = new Service(name);
        dao.create(service);
        return "/admin/admin_index";
    }
}
