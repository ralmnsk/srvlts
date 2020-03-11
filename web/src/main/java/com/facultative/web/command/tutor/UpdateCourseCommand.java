package com.facultative.web.command.tutor;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class UpdateCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        if(request.getSession().getAttribute(EDIT_COURSE) !=null ){
        Course editCourse=(Course)request.getSession().getAttribute(EDIT_COURSE);
            editCourse.setName(request.getParameter(COURSE));
            editCourse.setDescription(request.getParameter(DESCRIPTION));
            service.update(editCourse);

            return CONTROLLER_COMMAND_VIEW_COURSE;
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.edit"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
