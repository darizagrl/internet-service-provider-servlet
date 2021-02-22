package com.provider.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws ExceptionCommand, java.lang.Exception;
}
