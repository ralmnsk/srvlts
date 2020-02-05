package com.github.ralmnsk.srvlt.web.command.tuitor;

import com.github.ralmnsk.srvlt.model.Course;
import com.github.ralmnsk.srvlt.service.CourseService;
import com.github.ralmnsk.srvlt.service.CourseServiceInterface;
import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;
import com.github.ralmnsk.srvlt.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewcourse");
        CourseServiceInterface<Course> service= CourseService.getInstance();

        long userId=(long)request.getSession().getAttribute("userId");
        List<Course> list=service.getAllCourses(userId);
        request.setAttribute("list",list);

        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
