package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;
import com.provider.model.service.ServiceService;
import com.provider.model.service.TariffService;
import com.provider.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserMainCommand implements Command {
    UserService userService;
    TariffService tariffService;
    ServiceService serviceService;

    public UserMainCommand(UserService userService, TariffService tariffService, ServiceService serviceService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        user = userService.findUserTariffs(user);
        request.getSession().setAttribute("user", user);
        List<Service> serviceList = serviceService.findAll();
        int recordsPerPage = 3;
        int currentPage;
        int serviceId;
        String sortOrder;
        String sortField;
        List<Tariff> tariffList;
        if (request.getParameter("page") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("service_id") == null) {
            serviceId = serviceList.get(0).getId();
        } else {
            serviceId = Integer.parseInt(request.getParameter("service_id"));
        }

        if (request.getParameter("sort_field") == null || request.getParameter("sort_field").equals("name")) {
            sortField = "tariff.name";
        } else {
            sortField = "tariff.price";
        }
        if (request.getParameter("sort_order") == null || request.getParameter("sort_order").equals("asc")) {
            sortOrder = "asc";
        } else {
            sortOrder = "desc";
        }
        int numberOfPages = (int) Math.ceil(tariffService.findAllByServiceId(serviceId).size() * 1.0 / recordsPerPage);
        tariffList = tariffService.findPaginatedAndSorted(serviceId, sortField, sortOrder, currentPage, recordsPerPage);
        Service service = serviceService.findById(serviceId);
        request.setAttribute("sort_order", sortOrder);
        request.setAttribute("reverseSortOrder", sortOrder.equals("asc") ? "desc" : "asc");
        request.setAttribute("sort_field", sortField);
        request.setAttribute("serviceAttr", service);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("serviceList", serviceList);
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        return "/user/user_index.jsp";
    }
}
