package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.service.ServiceService;
import com.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateNewTariffCommand implements Command {
    private final Logger logger = LogManager.getLogger(CreateNewTariffCommand.class);
    ServiceService serviceService;
    TariffService tariffService;

    public CreateNewTariffCommand(ServiceService serviceService, TariffService tariffService) {
        this.serviceService = serviceService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Service> serviceList = serviceService.findAll();
        int serviceId;
        if (request.getParameter("serviceId") == null) {
            serviceId = serviceList.get(0).getId();
        } else {
            serviceId = Integer.parseInt(request.getParameter("serviceId"));
        }
        Service service = serviceService.findById(serviceId);
        request.setAttribute("serviceAttr", service);
        request.setAttribute("serviceList", serviceList);
        double price = 0.0;
        try {
            if (request.getParameter("price") == null) {
                request.setAttribute("price", price);
            } else {
                price = Double.parseDouble(request.getParameter("price"));
            }
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            request.setAttribute("message", "Wrong number format");
            return "/admin/tariff_add.jsp";
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (name == null || name.equals("") || description == null || description.equals("")) {
            return "/admin/tariff_add.jsp";
        }
        if (price < 0 || price > 10000) {
            logger.warn("The price is out of range [0, 10 000], price= {}", price);
            request.setAttribute("message", "The price has to be between 0 and 10 000");
            return "/admin/tariff_add.jsp";
        }

        if (name.length() < 2 || name.length() > 255) {
            logger.warn("The name length is out of range [2, 255] symbols, name= {}", name);
            request.setAttribute("message", "The name has to be between 2 and 255 symbols");
            return "/admin/tariff_add.jsp";
        }
        List<Tariff> list = tariffService.findAll();
        for (Tariff tariff : list) {
            if (name.equals(tariff.getName())) {
                logger.warn("The tariff with this name {} already exist ", name);
                request.setAttribute("message", "The tariff with this name already exist.");
                return "/admin/tariff_add.jsp";
            }
        }
        Tariff tariff = new Tariff(name, description, price);
        tariff.setService(service);
        tariffService.create(tariff);
        logger.info("The tariff with name {} was created", tariff.getName());
        return "/admin/admin_index";
    }
}
