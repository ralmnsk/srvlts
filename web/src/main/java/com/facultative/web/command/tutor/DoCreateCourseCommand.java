package com.facultative.web.command.tutor;

import com.facultative.model.Person;
import com.facultative.model.UserType;
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
    private Pagination pagination = new Pagination();

    @Override
    public String execute(HttpServletRequest request) {
        String courseName=request.getParameter(COURSE);
        String description=request.getParameter(DESCRIPTION);
        long userId=(long)request.getSession().getAttribute(USER_ID);

        Person tutor = personService.get(userId);
        Course course=new Course();

        if(tutor !=null ){
            course.setTutor(tutor);
            course.setName(courseName);
            course.setDescription(description);

            int scale = pagination.getScale(request);
            if(!isExist(userId, course, scale) && (tutor.getRole() == UserType.TUTOR)){
                courseService.save(course);
                return "/controller?command=viewcourse";
            } else{
                request.setAttribute(COURSE_EXISTS,MessageManager.getProperty("message.course.exists"));
            }
        }
            request.setAttribute(NULL_PAGE,MessageManager.getProperty("message.error.person"));
            return ConfigurationManager.getProperty("path.page.error");
    }

    private boolean isExist(long userId, Course course, int scale) {
        List<Course> coursesByTutorId = courseService.getCoursesByTutorId(userId,ALL_MARKS, scale);
        List<Course> list = coursesByTutorId.stream().filter(m->m.getName().equals(course.getName())).collect(Collectors.toList());

        return list.size() != 0;
    }
}
