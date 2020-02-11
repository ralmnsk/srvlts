package com.facultative.web.command.tutor;

import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DeleteCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);
        if(request.getSession().getAttribute(EDIT_COURSE) !=null ){
            Course editCourse = (Course)request.getSession().getAttribute(EDIT_COURSE);
            long editCourseId = editCourse.getId();
            service.delete(editCourseId);
            request.getSession().removeAttribute(EDIT_COURSE);
        }

        long userId = (long)request.getSession().getAttribute(USER_ID);
        List<Course> list = service.getCoursesByTutorId(userId, Pagination.getPageNumberTutorCourses(request,userId));
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.tutor");
    }
}
