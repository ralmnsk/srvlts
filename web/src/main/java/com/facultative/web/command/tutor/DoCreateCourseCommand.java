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
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.facultative.service.constants.Constants.*;

public class DoCreateCourseCommand implements ActionCommand {

    private IPersonService<Person> personService = PersonServiceImpl.getInstance();
    private ICourseService<Course> courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);
        String courseName=request.getParameter(COURSE);
        long userId=(long)request.getSession().getAttribute(USER_ID);

        Person tutor = personService.get(userId);
        Course course=new Course();

        if(tutor !=null ){
            course.setTutor(tutor);
        }

        course.setName(courseName);

        int scale = Pagination.getScale(request);
        if(!isExist(userId, course, scale)){
            courseService.save(course);
        } else{
            request.setAttribute(COURSE_EXISTS,MessageManager.getProperty("message.course.exists"));
        }
        List<Course> list=courseService.getCoursesByTutorId(userId, Pagination.getPageNumberTutorCourses(request,userId),scale);
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.tutor");
    }

    private boolean isExist(long userId, Course course, int scale) {
        List<Course> coursesByTutorId = courseService.getCoursesByTutorId(userId,ALL_MARKS, scale);
        List<Course> list = coursesByTutorId.stream().filter(m->m.getName().equals(course.getName())).collect(Collectors.toList());

        return list.size() != 0;
    }
}
