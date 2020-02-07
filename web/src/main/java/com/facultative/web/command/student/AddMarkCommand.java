package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.COURSE;
import static com.facultative.service.constants.Constants.COURSE_ID;

public class AddMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","addmark");
        long courseId=Long.parseLong(request.getParameter(COURSE_ID));
        request.setAttribute(COURSE_ID,courseId);
        ICourseService<Course> courseService= CourseServiceImpl.getInstance();
        Course course=courseService.get(courseId);
        request.getSession().setAttribute(COURSE,course);
        String page = ConfigurationManager.getProperty("path.page.student");
        return page;
    }
}