package com.github.ralmnsk.srvlt.web.command.tuitor;

import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;
import com.github.ralmnsk.srvlt.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class CreateCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","createcourse");
        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
