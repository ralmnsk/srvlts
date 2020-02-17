package com.facultative.web.command.tutor;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class UpdateCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        Course editCourse=(Course)request.getSession().getAttribute(EDIT_COURSE);
        if(editCourse !=null ){
            editCourse.setName(request.getParameter(COURSE));
            editCourse.setDescription(request.getParameter(DESCRIPTION));
            service.update(editCourse);
        }

        return "/controller?command=viewcourse";
    }
}
