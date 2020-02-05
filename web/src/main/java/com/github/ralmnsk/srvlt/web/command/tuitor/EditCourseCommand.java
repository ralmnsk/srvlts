package com.github.ralmnsk.srvlt.web.command.tuitor;

import com.github.ralmnsk.srvlt.model.Course;
import com.github.ralmnsk.srvlt.service.CourseService;
import com.github.ralmnsk.srvlt.service.CourseServiceInterface;
import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;
import com.github.ralmnsk.srvlt.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","editcourse");
        CourseServiceInterface<Course> service= CourseService.getInstance();
        long userId=(long)request.getSession().getAttribute("userId");
        long editId=Long.parseLong(request.getParameter("editid"));
        Course editCourse=service.get(editId);
        request.getSession().setAttribute("editCourse",editCourse);
        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
