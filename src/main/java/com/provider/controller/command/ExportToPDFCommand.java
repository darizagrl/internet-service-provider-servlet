package com.provider.controller.command;

import com.lowagie.text.DocumentException;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.TariffDao;
import com.provider.model.entity.Tariff;
import com.provider.model.entity.User;
import com.provider.utils.TariffPDFExporter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportToPDFCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException, SQLException, ClassNotFoundException {

        DaoFactory factory = DaoFactory.getInstance();
        TariffDao daoTariff = factory.getTariffDao();
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=tariffs_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Tariff> tariffList = daoTariff.findAll();

        TariffPDFExporter exporter = new TariffPDFExporter(tariffList);
        exporter.export(response);
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");
        int userRole = loggedUser.getRole().getId();

        switch (userRole) {
            case 1:
                return "/admin/admin_index";
            case 2:
                return "/user/user_index";
            default:
                return "/index";
        }
    }
}
