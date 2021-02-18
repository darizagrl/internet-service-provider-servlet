package com.provider.controller.command;

import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.dao.TariffDao;
import com.provider.model.entity.Service;
import com.provider.model.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class MainPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(MainPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws java.lang.Exception {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            ServiceDao dao = factory.getServiceDao();
            TariffDao daoTariff = factory.getTariffDao();
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
            return "/index.jsp";
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }
}