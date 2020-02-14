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
        if(request.getSession().getAttribute(EDIT_COURSE) !=null ){
            Course editCourse = (Course)request.getSession().getAttribute(EDIT_COURSE);
            long editCourseId = editCourse.getId();
            service.delete(editCourseId);
            request.getSession().removeAttribute(EDIT_COURSE);
        }

        return "/controller?command=viewcourse";
    }
}
