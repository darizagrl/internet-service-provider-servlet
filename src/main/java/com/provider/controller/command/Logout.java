package com.provider.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        request.getServletContext().removeAttribute("email");
        return "redirect:/index";
    }
}
