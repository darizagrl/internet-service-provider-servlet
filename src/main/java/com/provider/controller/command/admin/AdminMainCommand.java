package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class AdminMainCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            ServiceDao dao = factory.createServiceDao();
            TariffDao daoTariff = factory.createTariffDao();
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
            return "/admin/admin_index.jsp";
        } catch (SQLException e) {
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }
}
