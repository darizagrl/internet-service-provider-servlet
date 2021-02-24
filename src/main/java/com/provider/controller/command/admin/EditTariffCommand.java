package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.service.ServiceService;
import com.provider.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditTariffCommand implements Command {
    TariffService tariffService;
    ServiceService serviceService;

    public EditTariffCommand(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        List<Service> serviceList = serviceService.findAll();
        Tariff currentTariff = tariffService.findById(tariffId);
        String newName = request.getParameter("newName");
        String newDescription = request.getParameter("newDescription");
        int serviceId;
        if (request.getParameter("serviceId") == null) {
            serviceId = currentTariff.getService().getId();
        } else {
            serviceId = Integer.parseInt(request.getParameter("serviceId"));
        }
        double newPrice;
        try {
            if (request.getParameter("newPrice") == null) {
                newPrice = currentTariff.getPrice();
            } else {
                newPrice = Double.parseDouble(request.getParameter("newPrice"));
            }

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Wrong number format");
            request.setAttribute("tariff", currentTariff);
            request.setAttribute("currentService", currentTariff.getService());
            request.setAttribute("serviceList", serviceList);
            return "/admin/tariff_edit.jsp";
        }
        request.setAttribute("tariff", currentTariff);
        request.setAttribute("currentService", currentTariff.getService());
        request.setAttribute("serviceList", serviceList);

        if (newName == null || newName.equals("") || newDescription == null || newDescription.equals("")) {
            request.setAttribute("message", "You have empty fields.");
            return "/admin/tariff_edit.jsp";
        }
        if (newPrice < 0 || newPrice > 1000) {
            request.setAttribute("message", "Max price = 1000, min = 0");
            return "/admin/tariff_edit.jsp";
        }
        if (newName.length() < 4 || newName.length() > 255) {
            request.setAttribute("message", "Minimum length = 4.\n"
                    + "Maximum length = 255");
            return "/admin/tariff_edit.jsp";
        }
        List<Tariff> list = tariffService.findAll();
        if (!(newName.equals(currentTariff.getName()))) {
            for (Tariff tariff : list) {
                if (newName.equals(tariff.getName())) {
                    request.setAttribute("message", "This tariff name already exist.");
                    return "/admin/tariff_edit.jsp";
                }
            }
        }
        Service newService = serviceService.findById(serviceId);
        currentTariff.setName(newName);
        currentTariff.setDescription(newDescription);
        currentTariff.setPrice(newPrice);
        currentTariff.setService(newService);
        tariffService.update(currentTariff);
        return "/admin/admin_index";
    }
}
