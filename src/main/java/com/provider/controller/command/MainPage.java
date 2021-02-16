package com.provider.controller.command;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class MainPage implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            ServiceDao daoService = factory.createServiceDao();
            TariffDao daoTariff = factory.createTariffDao();

            List<Service> serviceList;
            serviceList = daoService.findAll();
            int serviceId;
            String sortBy;
            if(request.getParameter("serviceId")== null) {
                serviceId = serviceList.get(0).getId();
            }else {
                serviceId=Integer.parseInt(request.getParameter("serviceId"));
            }
            if(request.getParameter("sort")== null) {
                sortBy = "ByPrice";
            }else {
                sortBy=request.getParameter("sort");
            }
            List<Tariff> tariffList;
            switch (sortBy) {
                case "ByName(a-z)":
                    tariffList = daoTariff.findByServiceSortedASC(serviceId);
                    break;
                case "ByName(z-a)":
                    tariffList = daoTariff.findByServiceSortedDESC(serviceId);
                    break;
                case "ByPrice":
                    tariffList = daoTariff.findByServiceSortedByPrice(serviceId);
                    break;
                default:
                    tariffList = daoTariff.findAll();
                    break;
            }
            Service service = daoService.findById(serviceId);
            request.setAttribute("sort", sortBy);
            request.setAttribute("serviceAttr", service);
            request.setAttribute("tariffList", tariffList);
            request.setAttribute("serviceList", serviceList);
            return "/main.jsp";
        } catch (SQLException e) {
            throw new SQLException();
        } catch (java.lang.Exception e) {
            throw new ClassNotFoundException();
        }
    }
}