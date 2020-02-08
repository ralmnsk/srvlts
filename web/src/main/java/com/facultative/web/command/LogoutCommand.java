package com.facultative.web.command;

import com.facultative.service.config.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");

        request.getSession().invalidate();
        return page;
    }
}
