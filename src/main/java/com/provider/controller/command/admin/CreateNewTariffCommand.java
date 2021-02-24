package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.service.ServiceService;
import com.provider.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateNewTariffCommand implements Command {
    ServiceService serviceService;
    TariffService tariffService;

    public CreateNewTariffCommand(ServiceService serviceService, TariffService tariffService) {
        this.serviceService = serviceService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   {
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
        double price;
        try {
            if (request.getParameter("price") == null) {
                price = 0.0;
                request.setAttribute("price", 0);
            } else {
                price = Double.parseDouble(request.getParameter("price"));
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Wrong number format");
            return "/admin/tariff_add.jsp";
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (name == null || name.equals("") || description == null || description.equals("")) {
            return "/admin/tariff_add.jsp";
        }
        if (price < 0 || price > 10000) {
            request.setAttribute("message", "Max price = 10000, min = 0");
            return "/admin/tariff_add.jsp";
        }

        if (name.length() < 4 || name.length() > 255) {
            request.setAttribute("message", "Minimum length of name = 4.");
            return "/admin/tariff_add.jsp";
        }
        List<Tariff> list = tariffService.findAll();
        for (Tariff tariff : list) {
            if (name.equals(tariff.getName())) {
                request.setAttribute("message", "This tariff name already exist.");
                return "/admin/tariff_add.jsp";
            }
        }
        Tariff tariff = new Tariff(name, description, price);
        tariff.setService(service);
        tariffService.create(tariff);
        return "/admin/admin_index";
    }
}
