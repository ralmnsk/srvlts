package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DelMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,"delmark");

        long markId=Long.parseLong(request.getParameter(MARK_ID));
        request.getSession().setAttribute(MARK_ID,markId);
        IMarkService<Mark> markService=MarkServiceImpl.getInstance();
        Mark mark=markService.get(markId);
        Course course=mark.getCourse();
        request.getSession().setAttribute(COURSE,course);
        String page = ConfigurationManager.getProperty("path.page.student");
        return page;
    }
}
