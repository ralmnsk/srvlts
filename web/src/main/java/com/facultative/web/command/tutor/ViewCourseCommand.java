package com.facultative.web.command.tutor;

import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class ViewCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);

        int scale = Pagination.getScale(request);
        long userId = (long)request.getSession().getAttribute(USER_ID);
        int pageNumber= Pagination.getPageNumberTutorCourses(request,userId);
        List<Course> list=service.getCoursesByTutorId(userId,pageNumber,scale);//number of the page to go
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.tutor");
    }
}
