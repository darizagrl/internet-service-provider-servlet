package com.provider.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionCommand extends Throwable implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "error.jsp";
    }
}