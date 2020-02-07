package com.facultative.web.command.tutor;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EditCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","editcourse");
        ICourseService<Course> service= CourseServiceImpl.getInstance();
        long userId=(long)request.getSession().getAttribute("userId");
        long editId=Long.parseLong(request.getParameter("editid"));
        Course editCourse=service.get(editId);
        request.getSession().setAttribute("editCourse",editCourse);
        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
