package com.provider.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:/index.jsp";
    }
}
