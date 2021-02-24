package com.provider.controller;

import com.provider.controller.command.*;
import com.provider.controller.command.admin.*;
import com.provider.controller.command.user.*;
import com.provider.model.service.ServiceService;
import com.provider.model.service.TariffService;
import com.provider.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("/", new MainPageCommand(new ServiceService(), new TariffService()));
        commands.put("index", new MainPageCommand(new ServiceService(), new TariffService()));
        commands.put("login", new LoginCommand(new UserService()));
        commands.put("logout", new LogoutCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("index/export/pdf", new ExportToPDFCommand(new TariffService()));

        commands.put("admin/", new AdminMainCommand(new ServiceService(), new TariffService()));
        commands.put("admin/admin_index", new AdminMainCommand(new ServiceService(), new TariffService()));
        commands.put("admin/user_management", new UserManagementCommand(new UserService()));
        commands.put("admin/registration", new RegistrationCommand(new UserService()));
        commands.put("admin/user_delete", new DeleteUserCommand(new UserService()));
        commands.put("admin/tariff_add", new CreateNewTariffCommand(new ServiceService(), new TariffService()));
        commands.put("admin/tariff_delete", new DeleteTariffCommand(new TariffService()));
        commands.put("admin/tariff_edit", new EditTariffCommand(new TariffService(), new ServiceService()));
        commands.put("admin/service_add", new CreateNewServiceCommand(new ServiceService()));
        commands.put("admin/admin_index/export/pdf", new ExportToPDFCommand(new TariffService()));

        commands.put("user/user_index", new UserMainCommand(new UserService(), new TariffService(), new ServiceService()));
        commands.put("user/subscribe", new SubscribeTariffCommand(new UserService(), new TariffService()));
        commands.put("user/account", new AccountCommand(new UserService()));
        commands.put("user/account_replenishment", new AccountReplenishmentCommand(new UserService()));
        commands.put("user/unsubscribe", new UnsubscribeCommand(new UserService()));
        commands.put("user/user_index/export/pdf", new ExportToPDFCommand(new TariffService()));
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getRequestURI();
            path = path.replaceAll("/internet_provider/", "");
            Command command = commands.get(path);
            String page;
            page = command.execute(request, response);

            if (page.contains("redirect:")) {
                response.sendRedirect(page.replace("redirect:", "/internet_provider"));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (java.lang.Exception | ExceptionCommand exception) {
            exception.printStackTrace();
        }
    }
}
