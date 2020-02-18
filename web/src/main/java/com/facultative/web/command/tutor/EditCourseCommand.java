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

        long editId = 0;
        if (request.getParameter(EDIT_ID) != null) {      //when command lang is executed editId(Parameter) in request will be 0;
            editId = Long.parseLong(request.getParameter(EDIT_ID));     //and error page will not be returned
            request.getSession().setAttribute(EDIT_ID, editId);
        } else if (request.getSession().getAttribute(EDIT_ID) != null){
            editId = (long)request.getSession().getAttribute(EDIT_ID);
        }

        if (editId > 0){
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
