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

        long courseId = 0;
        if (request.getParameter(COURSE_ID) != null) {      //when command lang is executed markId(Parameter) in request will be 0;
            courseId = Long.parseLong(request.getParameter(COURSE_ID));     //and error page will not be returned
            request.getSession().setAttribute(COURSE_ID, courseId);
        } else if (request.getSession().getAttribute(COURSE_ID) != null){
            courseId = (long)request.getSession().getAttribute(COURSE_ID);
        }

        if (courseId > 0){
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
