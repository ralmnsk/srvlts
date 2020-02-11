package com.facultative.web.command.tutor;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class EditCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,EDIT_COURSE);

        long editId = Long.parseLong(request.getParameter(EDIT_ID));
        Course editCourse = service.get(editId);
        if (editCourse != null ){
            request.getSession().setAttribute(EDIT_COURSE,editCourse);
        }

        return ConfigurationManager.getProperty("path.page.tutor");
    }
}
