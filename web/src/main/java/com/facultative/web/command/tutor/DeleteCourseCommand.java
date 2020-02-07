package com.facultative.web.command.tutor;

import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewcourse");
        ICourseService<Course> service= CourseServiceImpl.getInstance();
        Course editCourse=(Course)request.getSession().getAttribute("editCourse");
        long editCourseId=editCourse.getId();
        service.delete(editCourseId);
        request.getSession().removeAttribute("editCourse");

        long userId=(long)request.getSession().getAttribute("userId");
        List<Course> list=service.getCoursesByTutorId(userId);
        request.setAttribute("list",list);

        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
