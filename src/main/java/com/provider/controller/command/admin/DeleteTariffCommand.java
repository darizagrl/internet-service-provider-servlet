package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariffCommand implements Command {
    TariffService tariffService;

    public DeleteTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("tariffId"));
        tariffService.delete(id);
        return "/admin/admin_index";
    }
}
