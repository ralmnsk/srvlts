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
import com.facultative.web.command.pagination.Scale;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.facultative.service.constants.Constants.*;

public class DoCreateCourseCommand implements ActionCommand {

    private IPersonService<Person> personService = PersonServiceImpl.getInstance();
    private ICourseService<Course> courseService = CourseServiceImpl.getInstance();
    private Scale scaleFinder = new Scale();

    @Override
    public String execute(HttpServletRequest request) {
        String courseName=request.getParameter(COURSE);
        String description=request.getParameter(DESCRIPTION);
        long userId=(long)request.getSession().getAttribute(USER_ID);

        Person tutor = personService.get(userId);

        if(tutor !=null ){
            Course course=new Course();
            course.setTutor(tutor);
            course.setName(courseName);
            course.setDescription(description);

            int scale = scaleFinder.getScale(request);
            if(!isExist(userId, course, scale) && (tutor.getRole() == UserType.TUTOR)){
                courseService.save(course);
                request.setAttribute(SEND_REDIRECT,true);

                return CONTROLLER_COMMAND_VIEW_COURSE;
            } else{
                request.setAttribute(COURSE_EXISTS,MessageManager.getProperty("message.course.exists"));
                return ConfigurationManager.getProperty("path.page.error");
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
