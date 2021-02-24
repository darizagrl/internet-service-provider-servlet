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
        int serviceId;
        String sortBy;
        if (request.getParameter("serviceId") == null) {
            serviceId = serviceList.get(0).getId();
        } else {
            serviceId = Integer.parseInt(request.getParameter("serviceId"));
        }
        if (request.getParameter("sort") == null) {
            sortBy = "By Name(a-z)";
        } else {
            sortBy = request.getParameter("sort");
        }
        List<Tariff> tariffList;
        switch (sortBy) {
            case "By Name(a-z)":
                tariffList = tariffService.findByServiceSortedASC(serviceId);
                break;
            case "By Name(z-a)":
                tariffList = tariffService.findByServiceSortedDESC(serviceId);
                break;
            case "By Price(asc)":
                tariffList = tariffService.findByServiceSortedByPriceASC(serviceId);
                break;
            case "By Price(desc)":
                tariffList = tariffService.findByServiceSortedByPriceDESC(serviceId);
                break;
            default:
                tariffList = tariffService.findAllByServiceId(serviceId);
                break;
        }
        Service service = serviceService.findById(serviceId);
        request.setAttribute("sort", sortBy);
        request.setAttribute("serviceAttr", service);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("serviceList", serviceList);
        return "/user/user_index.jsp";
    }
}
