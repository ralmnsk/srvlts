package com.github.ralmnsk.srvlt.web.command.student;

import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;
import com.github.ralmnsk.srvlt.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class StudentCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.student");
        return page;
    }
}
