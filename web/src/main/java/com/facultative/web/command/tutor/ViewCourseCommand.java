package com.facultative.web.command.tutor;

import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.pagination.IPagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class ViewCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,"viewcourse");
        ICourseService<Course> service= CourseServiceImpl.getInstance();
        long userId=(long)request.getSession().getAttribute(USER_ID);
        int pageNumber= IPagination.getPageNumberTutorCourses(request,userId);
        List<Course> list=service.getCoursesByTutorId(userId,pageNumber);
        request.setAttribute(LIST_JSP,list);

        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
