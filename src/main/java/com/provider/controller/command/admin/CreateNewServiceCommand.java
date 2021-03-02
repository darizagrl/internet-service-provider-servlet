package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateNewServiceCommand implements Command {
    private final Logger logger = LogManager.getLogger(CreateNewServiceCommand.class);
    ServiceService serviceService;

    public CreateNewServiceCommand(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   {
        String name = request.getParameter("name");
        logger.info("Creating the service with name {}", name);
        if (name == null || name.equals("")) {
            return "/admin/service_add.jsp";
        }
        List<Service> list = serviceService.findAll();
        for (Service service : list) {
            if (name.equals(service.getName())) {
                logger.warn("The service with this name {} already exist ", name);
                request.setAttribute("message", "The service with this name already exist.");
                return "/admin/service_add.jsp";
            }
        }
        Service service = new Service(name);
        serviceService.create(service);
        logger.info("The service with name {} was created", service.getName());
        return "/admin/admin_index";
    }
}
