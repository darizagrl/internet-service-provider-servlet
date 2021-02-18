package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.dao.DaoFactory;
import com.provider.model.dao.ServiceDao;
import com.provider.model.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CreateNewServiceCommand implements Command {
    private final Logger logger = LogManager.getLogger(CreateNewServiceCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        try {
            String name = request.getParameter("name");
            if (name == null || name.equals("")) {
                request.setAttribute("message", "You have empty fields.");
                return "/admin/service_add.jsp";
            }
            if (name.length() < 4 || name.length() > 12) {
                request.setAttribute("message", "Minimum length of login and password = 4.\n"
                        + "Maximum length of login and password = 12");
                return "/admin/service_add.jsp";
            }
            DaoFactory factory = DaoFactory.getInstance();
            ServiceDao dao = factory.getServiceDao();
            List<Service> list = dao.findAll();
            for (Service service : list) {
                if (name.equals(service.getName())) {
                    request.setAttribute("message", "This name already exist.");
                    return "/admin/service_add.jsp";
                }
            }
            Service service = new Service(name);
            dao.create(service);
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
