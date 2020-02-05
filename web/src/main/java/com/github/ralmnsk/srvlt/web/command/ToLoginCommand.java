package com.github.ralmnsk.srvlt.web.command;

import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToLoginCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}