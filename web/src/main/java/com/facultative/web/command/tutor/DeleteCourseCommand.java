package com.facultative.web.command.tutor;

import com.facultative.model.Person;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class DeleteCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();
    private IPersonService<Person> personService = PersonServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if(request.getSession().getAttribute(EDIT_COURSE) !=null ){
            Course editCourse = (Course)request.getSession().getAttribute(EDIT_COURSE);
            long editCourseId = editCourse.getId();
            if (editCourseId != 0L ){
                if ( request.getSession().getAttribute(USER_ID) != null){
                    Person user = personService.get((Long)request.getSession().getAttribute(USER_ID));
                    if (user != null){
                        if(user.getId() == editCourse.getTutor().getId()){
                            service.delete(editCourseId);
                            request.getSession().removeAttribute(EDIT_COURSE);

                            return CONTROLLER_COMMAND_VIEW_COURSE;
                        }
                    }
                }
            }
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.del"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
