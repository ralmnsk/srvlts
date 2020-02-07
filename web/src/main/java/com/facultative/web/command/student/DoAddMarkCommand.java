package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.model.Student;
import com.facultative.service.*;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.facultative.service.constants.Constants.*;

public class DoAddMarkCommand implements ActionCommand {
    private IMarkService markService=MarkServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("processFlag","viewmark");
        Course course=(Course)request.getSession().getAttribute(COURSE);
        long studentId=(long)request.getSession().getAttribute(USER_ID);
        ICourseService<Course> courseService=CourseServiceImpl.getInstance();
//        Course course=courseService.get(courseId);

        IPersonService<Person> personService=PersonServiceImpl.getInstance();
        Person person=personService.get(studentId);
        Student student=new Student();
        student.setId(person.getId());
        student.setSurname(person.getSurname());
        student.setName(person.getName());

        Mark mark=new Mark();
        mark.setStudent(student);
        mark.setCourse(course);


        if(!isEnrolled(mark,studentId)){
            markService.save(mark);
        }

        List<Mark> list=markService.getMarksByStudentId(studentId);
        request.setAttribute(LIST_JSP,list);

        String page = ConfigurationManager.getProperty("path.page.student");
        return page;
    }

    private boolean isEnrolled(Mark mark,long studentId) {
        List<Mark> marksByStudentId = markService.getMarksByStudentId(studentId);
        List<Mark> list=marksByStudentId.stream().filter(m->m.getCourse().getName().equals(mark.getCourse().getName())).collect(Collectors.toList());
        if(list.size()==0){
            return false;
        }
        return true;
    }
}
