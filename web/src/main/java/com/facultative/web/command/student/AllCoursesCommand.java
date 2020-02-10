package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Person;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.IPersonService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class AllCoursesCommand implements ActionCommand {

    private ICourseService<Course> courseService;
    private IPersonService<Person> personService;

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);
        List<Course> list;
        courseService = CourseServiceImpl.getInstance();
        personService = PersonServiceImpl.getInstance();
        int pageNumber= Pagination.getPageNumberAllCourses(request);
        list=courseService.getCourses(pageNumber);
        for (Course course:list){
            long userId=course.getTutor().getId();
            Person tutor=personService.get(userId);

            if(tutor != null){
                course.setTutor(tutor);
            }
        }
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.student");
    }
}
