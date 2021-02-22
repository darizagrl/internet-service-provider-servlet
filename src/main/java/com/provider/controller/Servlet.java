package com.provider.controller;

import com.provider.controller.command.*;
import com.provider.controller.command.admin.*;
import com.provider.controller.command.user.*;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("", new MainPageCommand());
        commands.put("index", new MainPageCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("exception", new ExceptionCommand());

        commands.put("admin/", new AdminMainCommand());
        commands.put("admin/admin_index", new AdminMainCommand());
        commands.put("admin/user_management", new UserManagementCommand());
        commands.put("admin/registration", new RegistrationCommand());
        commands.put("admin/user_delete", new DeleteUserCommand());
        commands.put("admin/tariff_add", new CreateNewTariffCommand());
        commands.put("admin/tariff_delete", new DeleteTariffCommand());
        commands.put("admin/tariff_edit", new EditTariffCommand());
        commands.put("admin/service_add", new CreateNewServiceCommand());

        commands.put("user/user_index", new UserMainCommand());
        commands.put("user/subscribe", new SubscribeTariffCommand());
        commands.put("user/account", new AccountCommand());
        commands.put("user/account_replenishment", new AccountReplenishmentCommand());
        commands.put("user/unsubscribe", new UnsubscribeCommand());
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
            page = command.execute(request);

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
