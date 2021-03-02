package com.provider.controller.command.admin;

import com.provider.controller.command.Command;
import com.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariffCommand implements Command {
    private final Logger logger = LogManager.getLogger(DeleteTariffCommand.class);
    TariffService tariffService;

    public DeleteTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("tariffId"));
        logger.info("Deleting the tariff with id={}", id);
        tariffService.delete(id);
        logger.info("The tariff with id={} was deleted", id);
        return "/admin/admin_index";
    }
}
