package com.provider.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand extends Throwable implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/error.jsp";
    }
}