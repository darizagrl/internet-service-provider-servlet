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

public class EditTariffCommand implements Command {
    private final Logger logger = LogManager.getLogger(EditTariffCommand.class);
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
        logger.info("Updating the tariff with id={}", currentTariff.getId());
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
            logger.error(e.getMessage());
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
            request.setAttribute("message", "You have empty fields");
            return "/admin/tariff_edit.jsp";
        }
        if (newPrice < 0 || newPrice > 10000) {
            logger.warn("The price is out of range [0, 10 000], price= {}", newPrice);
            request.setAttribute("message", "The price has to be between 0 and 10 000");
            return "/admin/tariff_edit.jsp";
        }
        if (newName.length() < 2 || newName.length() > 255) {
            logger.warn("The name length is out of range [2, 255] symbols, name= {}", newName);
            request.setAttribute("message", "The name has to be between 2 and 255 symbols");
            return "/admin/tariff_edit.jsp";
        }
        List<Tariff> list = tariffService.findAll();
        if (!(newName.equals(currentTariff.getName()))) {
            for (Tariff tariff : list) {
                if (newName.equals(tariff.getName())) {
                    logger.warn("The tariff with this name {} already exist ", newName);
                    request.setAttribute("message", "The tariff with this name already exist.");
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
        logger.info("The tariff with id={} was updated", currentTariff.getId());
        return "/admin/admin_index";
    }
}
