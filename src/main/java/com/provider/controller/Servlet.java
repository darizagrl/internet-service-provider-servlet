package com.provider.controller;

import com.provider.controller.command.*;
import com.provider.controller.command.Exception;
import com.provider.controller.command.admin.AdminMain;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("", new MainPage());
        commands.put("main", new MainPage());
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("registration", new Registration());
        commands.put("exception", new Exception());

        commands.put("admin/", new AdminMain());
        commands.put("admin/adminMain", new AdminMain());
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getRequestURI();
//            System.out.println(path);
            path = path.replaceAll(".*/app/", "");
//            System.out.println(path);
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
