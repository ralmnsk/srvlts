package com.facultative.web.command.tutor;

import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class TutorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty("path.page.tutor");
    }
}
