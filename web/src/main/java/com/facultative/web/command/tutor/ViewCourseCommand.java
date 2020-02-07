package com.facultative.web.command.tutor;

import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;
import com.facultative.service.config.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.USER_ID;

public class ViewCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewcourse");
        ICourseService<Course> service= CourseServiceImpl.getInstance();

        long userId=(long)request.getSession().getAttribute(USER_ID);
        List<Course> list=service.getCoursesByTutorId(userId);
        request.setAttribute("list",list);

        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
