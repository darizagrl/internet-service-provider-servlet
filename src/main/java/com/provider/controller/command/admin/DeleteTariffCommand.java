package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.TariffDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DaoFactory factory = DaoFactory.getInstance();
        TariffDao dao = factory.getTariffDao();
        int id = Integer.parseInt(request.getParameter("tariffId"));
        dao.delete(id);
        return "/admin/admin_index";
    }
}
