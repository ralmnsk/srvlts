package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class AddMarkCommand implements ActionCommand {

    private ICourseService<Course> courseService= CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,ADD_MARK);
        if (request.getParameter(COURSE_ID) != null){
            long courseId=Long.parseLong(request.getParameter(COURSE_ID));
            request.setAttribute(COURSE_ID,courseId);

            Course course=courseService.get(courseId);
            if(course != null){
                request.getSession().setAttribute(COURSE,course);

                return ConfigurationManager.getProperty("path.page.student");
            }

        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.add"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
