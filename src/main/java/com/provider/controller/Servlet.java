package com.provider.controller;

import com.provider.controller.command.Exception;
import com.provider.controller.command.*;
import com.provider.controller.command.admin.AdminMainCommand;
import com.provider.controller.command.admin.CreateNewTariffCommand;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("", new MainPage());
        commands.put("index", new MainPage());
        commands.put("main", new MainPage());
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("registration", new Registration());
        commands.put("exception", new Exception());

        commands.put("admin/", new AdminMainCommand());
        commands.put("admin/admin_index", new AdminMainCommand());
        commands.put("admin/userManagement", new AdminMainCommand());
        commands.put("admin/showNewTariff", new CreateNewTariffCommand());
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
            path = path.replaceAll(".*/app/", "");
            Command command = commands.get(path);
            String page;
            page = command.execute(request);

            if (page.contains("redirect:")) {
                response.sendRedirect(page.replace("redirect:", "/api"));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (java.lang.Exception | Exception exception) {
            exception.printStackTrace();
        }
    }
}
