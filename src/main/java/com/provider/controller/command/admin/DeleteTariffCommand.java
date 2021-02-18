package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.TariffDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteTariffCommand implements Command {
    private final Logger logger = LogManager.getLogger(DeleteTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            TariffDao dao = factory.createTariffDao();
            int id = Integer.parseInt(request.getParameter("tariffId"));
            dao.delete(id);
            return "/admin/admin_index";
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ClassNotFoundException();
        }
    }
}
