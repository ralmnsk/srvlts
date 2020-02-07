package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Person;
import com.facultative.model.Tutor;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.IPersonService;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AllCoursesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewcourse");
        List<Course> list=new ArrayList<Course>();
        ICourseService<Course> courseService= CourseServiceImpl.getInstance();
        IPersonService<Person> personService= PersonServiceImpl.getInstance();
        list=courseService.getCourses();
        for (int i=0;i<list.size();i++){
            long userId=list.get(i).getTutor().getId();
            Person person=personService.get(userId);

            Tutor tutor=new Tutor();
            tutor.setId(person.getId());
            tutor.setSurname(person.getSurname());
            tutor.setName(person.getName());
            tutor.setLogin(person.getLogin());
            tutor.setPassword(person.getPassword());
            tutor.setRole(person.getRole());

            list.get(i).setTutor(tutor);
        }
        request.setAttribute("list",list); //all courses
        String page = ConfigurationManager.getProperty("path.page.student");
        return page;
    }
}
