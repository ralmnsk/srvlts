package com.facultative.web.command.tutor;

import com.facultative.model.Person;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.facultative.service.constants.Constants.*;

public class DoCreateCourseCommand implements ActionCommand {

    private IPersonService<Person> personService;
    private ICourseService<Course> courseService;

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);
        String courseName=request.getParameter(COURSE);
        long userId=(long)request.getSession().getAttribute(USER_ID);
        personService = PersonServiceImpl.getInstance();
        Person tutor = personService.get(userId);
        Course course=new Course();

        if(tutor !=null ){
            course.setTutor(tutor);
        }

        course.setName(courseName);
        courseService = CourseServiceImpl.getInstance();
        if(!isExist(userId, course)){
            courseService.save(course);
        }
        List<Course> list=courseService.getCoursesByTutorId(userId, Pagination.getPageNumberTutorCourses(request,userId));
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.tutor");
    }

    private boolean isExist(long userId, Course course) {
        List<Course> coursesByTutorId = courseService.getCoursesByTutorId(userId,ALL_MARKS);
        List<Course> list = coursesByTutorId.stream().filter(m->m.getName().equals(course.getName())).collect(Collectors.toList());

        return list.size() != 0;
    }
}
