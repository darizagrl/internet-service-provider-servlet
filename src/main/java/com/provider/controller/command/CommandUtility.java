package com.provider.controller.command;

import com.provider.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            String role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
//        session.setAttribute("email", email);
        session.setAttribute("role", role);
    }

    static void setUserAsAttribute(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

//    public static void deleteUserFromContext(ServletContext context) {
//        String email = (String) context.getAttribute("email");
//        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
//        loggedUsers.remove(email);
//        //for debug
//        System.out.println(email);
//        context.setAttribute("loggedUsers", loggedUsers);
//    }
//
//    public static boolean checkUserLogged(HttpServletRequest request, String email) {
//        ServletContext context = request.getSession().getServletContext();
//        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
//        if (loggedUsers.stream().anyMatch(email::equals)) {
//            return true;
//        }
//        loggedUsers.add(email);
//        context.setAttribute("loggedUsers", loggedUsers);
//        return false;
//    }
}
