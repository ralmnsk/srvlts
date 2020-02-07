package com.facultative.web.command.tutor;

import com.facultative.model.Person;
import com.facultative.model.Tutor;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.COURSE;
import static com.facultative.service.constants.Constants.USER_ID;

public class DoCreateCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewcourse");
        String courseName=request.getParameter(COURSE);
        long userId=(long)request.getSession().getAttribute(USER_ID);
        IPersonService<Person> personService= PersonServiceImpl.getInstance();
        Person person=personService.get(userId);

        Tutor tutor=new Tutor();
        tutor.setId(person.getId());
        tutor.setSurname(person.getSurname());
        tutor.setName(person.getName());
        tutor.setLogin(person.getLogin());
        tutor.setPassword(person.getPassword());
        tutor.setRole(person.getRole());

        Course course=new Course();
        course.setTutor(tutor);
        course.setName(courseName);
        ICourseService<Course> service= CourseServiceImpl.getInstance();
        service.save(course);
        List<Course> list=service.getCoursesByTutorId(userId);
        request.setAttribute("list",list);
        String page = ConfigurationManager.getProperty("path.page.tuitor");
        return page;
    }
}
