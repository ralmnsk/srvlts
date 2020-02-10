package com.facultative.web.command.student;

import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

public class StudentCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.student");
    }
}
