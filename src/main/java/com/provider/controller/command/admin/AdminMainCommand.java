package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.service.ServiceService;
import com.provider.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminMainCommand implements Command {
    ServiceService serviceService;
    TariffService tariffService;

    public AdminMainCommand(ServiceService serviceService, TariffService tariffService) {
        this.serviceService = serviceService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Service> serviceList = serviceService.findAll();
        int serviceId;
        String sortOrder;
        String sortField;
        List<Tariff> tariffList;
        if (request.getParameter("service_id") == null) {
            serviceId = serviceList.get(0).getId();
        } else {
            serviceId = Integer.parseInt(request.getParameter("service_id"));
        }
        if (request.getParameter("sort_field") == null) {
            sortField = "name";
        } else {
            sortField = request.getParameter("sort_field");
        }
        if (sortField.equalsIgnoreCase("name")) {
            if (request.getParameter("sort_order") == null || request.getParameter("sort_order").equals("asc")) {
                sortOrder = "asc";
                tariffList = tariffService.findByServiceSortedASC(serviceId);
            } else {
                sortOrder = "desc";
                tariffList = tariffService.findByServiceSortedDESC(serviceId);
            }
        } else if (sortField.equalsIgnoreCase("price")) {
            if (request.getParameter("sort_order") == null || request.getParameter("sort_order").equals("asc")) {
                sortOrder = "asc";
                tariffList = tariffService.findByServiceSortedByPriceASC(serviceId);
            } else {
                sortOrder = "desc";
                tariffList = tariffService.findByServiceSortedByPriceDESC(serviceId);
            }
        } else {
            sortOrder = "asc";
            tariffList = tariffService.findByServiceSortedASC(serviceId);
        }
        Service service = serviceService.findById(serviceId);
        request.setAttribute("sort_order", sortOrder);
        request.setAttribute("reverseSortOrder", sortOrder.equals("asc") ? "desc" : "asc");
        request.setAttribute("sort_field", sortField);
        request.setAttribute("serviceAttr", service);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("serviceList", serviceList);
        return "/admin/admin_index.jsp";
    }
    //TODO pagination
}
