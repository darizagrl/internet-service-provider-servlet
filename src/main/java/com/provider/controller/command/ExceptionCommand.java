package com.provider.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand extends Throwable implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
