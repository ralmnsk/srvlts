package com.facultative.web.command.tutor;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class EditCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,EDIT_COURSE);

        if (request.getParameter(EDIT_ID) != null){
            long editId = Long.parseLong(request.getParameter(EDIT_ID));
            Course editCourse = service.get(editId);
            if (editCourse != null ){
                if(request.getSession().getAttribute(USER_ID) != null){
                    long userId = (long)request.getSession().getAttribute(USER_ID);
                    long userIdFromEditCourse = editCourse.getTutor().getId();
                    if (userId == userIdFromEditCourse){
                        request.getSession().setAttribute(EDIT_COURSE,editCourse);
                        return ConfigurationManager.getProperty("path.page.tutor");
                    }
                }
            }
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.edit"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
