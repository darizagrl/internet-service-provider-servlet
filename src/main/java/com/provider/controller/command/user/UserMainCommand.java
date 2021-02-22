package com.provider.controller.command.user;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.dao.UserDao;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class UserMainCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DaoFactory factory = DaoFactory.getInstance();
        ServiceDao dao = factory.getServiceDao();
        TariffDao daoTariff = factory.getTariffDao();
        UserDao daoUser = factory.getUserDao();
        User user = (User) request.getSession().getAttribute("user");
        user = daoUser.findUserTariffs(user);
        request.getSession().setAttribute("user", user);
        List<Service> serviceList = dao.findAll();
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
                tariffList = daoTariff.findByServiceSortedASC(serviceId);
                break;
            case "By Name(z-a)":
                tariffList = daoTariff.findByServiceSortedDESC(serviceId);
                break;
            case "By Price(asc)":
                tariffList = daoTariff.findByServiceSortedByPriceASC(serviceId);
                break;
            case "By Price(desc)":
                tariffList = daoTariff.findByServiceSortedByPriceDESC(serviceId);
                break;
            default:
                tariffList = daoTariff.findAllByServiceId(serviceId);
                break;
        }
        Service service = dao.findById(serviceId);
        request.setAttribute("sort", sortBy);
        request.setAttribute("serviceAttr", service);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("serviceList", serviceList);
        return "/user/user_index.jsp";
    }
}
