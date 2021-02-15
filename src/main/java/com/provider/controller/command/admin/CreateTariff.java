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

public class CreateTariff implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            ServiceDao serviceDao = factory.createServiceDao();
            List<Service> serviceList = serviceDao.findAll();
            int serviceId;
            if (request.getParameter("serviceId") == null) {
                serviceId = serviceList.get(0).getId();
            } else {
                serviceId = Integer.parseInt(request.getParameter("serviceId"));
            }
            Service service = serviceDao.findById(serviceId);
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
                return "/admin/createTariff.jsp";
            }
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            if (name == null || name.equals("") || description == null || description.equals("")) {
                request.setAttribute("message", "You have empty fields.");
                return "/admin/createTariff.jsp";
            }
            if (price < 0 || price > 10000) {
                request.setAttribute("message", "Max price = 10000, min = 0");
                return "/admin/createTariff.jsp";
            }

            if (name.length() < 4 || name.length() > 255) {
                request.setAttribute("message", "Minimum length of name = 4.");
                return "/admin/createTariff.jsp";
            }

            TariffDao dao = factory.createTariffDao();
            List<Tariff> list = dao.findAll();
            for (Tariff tariff : list) {
                if (name.equals(tariff.getName())) {
                    request.setAttribute("message", "This tariff name already exist.");
                    return "/admin/createTariff.jsp";
                }
            }
            Tariff tariff = new Tariff(name, description, price);
            tariff.setService(service);
            dao.create(tariff);
            return "/admin/adminMain";
        } catch (SQLException e) {
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }
}
