package com.facultative.web.command;

import com.facultative.service.config.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToRegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        return page;
    }
}
