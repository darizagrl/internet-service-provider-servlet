package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateNewServiceCommand implements Command {
    ServiceService serviceService;

    public CreateNewServiceCommand(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   {
        String name = request.getParameter("name");
        if (name == null || name.equals("")) {
            return "/admin/service_add.jsp";
        }
        List<Service> list = serviceService.findAll();
        for (Service service : list) {
            if (name.equals(service.getName())) {
                request.setAttribute("message", "The service with this name already exist.");
                return "/admin/service_add.jsp";
            }
        }
        Service service = new Service(name);
        serviceService.create(service);
        return "/admin/admin_index";
    }
}
