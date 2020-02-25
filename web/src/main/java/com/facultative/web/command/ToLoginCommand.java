package com.facultative.web.command;

import com.facultative.service.config.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class ToLoginCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.login");
    }
}
