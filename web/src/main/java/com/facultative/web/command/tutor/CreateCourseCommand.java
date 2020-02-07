package com.facultative.web.command.tutor;

import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class CreateCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","createcourse");
        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
