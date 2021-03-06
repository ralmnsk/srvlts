package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.IPersonService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;
import com.facultative.web.command.pagination.Scale;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.facultative.service.constants.Constants.*;

public class AllCoursesCommand implements ActionCommand {

    private ICourseService<Course> courseService = CourseServiceImpl.getInstance();
    private IPersonService<Person> personService = PersonServiceImpl.getInstance();
    private Pagination pagination = new Pagination();
    private Scale scaleFinder = new Scale();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);

        int pageNumber = pagination.getPageNumber(request,NO_NUMBER,PAGE_ALL_COURSES_NUMBER);
        List<Course> list=courseService.getCourses(pageNumber,scaleFinder.getScale(request)); //scale = items on the page

        if (request.getSession().getAttribute(PERSON) !=null){
            Person user=(Person)request.getSession().getAttribute(PERSON);
                    if ((user.getRole() == UserType.STUDENT) || (user.getRole() == UserType.TUTOR)){
                        request.setAttribute(PROCESS_FLAG,ALL_COURSES); //pagination all courses (Command of student) are made by tutor
                        request.setAttribute(LIST_JSP,list);

                        return ConfigurationManager.getProperty("path.page.student");
                    }
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.courses"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
