package com.facultative.web.command.tutor;

import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.CREATE_COURSE;
import static com.facultative.service.constants.Constants.PROCESS_FLAG;

public class CreateCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,CREATE_COURSE);

        return ConfigurationManager.getProperty("path.page.tutor");
    }
}
