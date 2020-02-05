package com.github.ralmnsk.srvlt.web.command.tuitor;

import com.github.ralmnsk.srvlt.dao.DaoCourseInterface;
import com.github.ralmnsk.srvlt.model.Course;
import com.github.ralmnsk.srvlt.service.CourseService;
import com.github.ralmnsk.srvlt.service.CourseServiceInterface;
import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;
import com.github.ralmnsk.srvlt.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DoCreateCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewcourse");
        String courseName=request.getParameter("course");
        long userId=(long)request.getSession().getAttribute("userId");
        Course course=new Course();
        course.setTuitorId(userId);
        course.setName(courseName);
        CourseServiceInterface<Course> service=CourseService.getInstance();
        service.save(course);
        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
