package com.provider.controller;

import com.provider.controller.command.Exception;
import com.provider.controller.command.*;
import com.provider.controller.command.admin.AdminMainCommand;
import com.provider.controller.command.admin.CreateNewTariffCommand;
import com.provider.controller.command.admin.DeleteTariffCommand;

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
        commands.put("main", new MainPageCommand());
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("registration", new Registration());
        commands.put("exception", new Exception());

        commands.put("admin/", new AdminMainCommand());
        commands.put("admin/admin_index", new AdminMainCommand());
        commands.put("admin/user_management", new AdminMainCommand());
        commands.put("admin/new_tariff", new CreateNewTariffCommand());
        commands.put("admin/delete_tariff", new DeleteTariffCommand());
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
        } catch (java.lang.Exception | Exception exception) {
            exception.printStackTrace();
        }
    }
}
