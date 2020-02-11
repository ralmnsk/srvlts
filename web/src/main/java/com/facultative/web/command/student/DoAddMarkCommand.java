package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.service.*;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.facultative.service.constants.Constants.*;

public class DoAddMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();
    private IPersonService<Person> personService = PersonServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_MARK);
        Course course = (Course)request.getSession().getAttribute(COURSE);
        long studentId = (long)request.getSession().getAttribute(USER_ID);

        Person student = personService.get(studentId);
        Mark mark = new Mark();
        if (student != null){
            mark.setStudent(student);
            mark.setCourse(course);
        }


        if(!isEnrolled(mark,studentId)){
            markService.save(mark);
        }

        int pageNumber = Pagination.getPageNumberStudentCourses(request,studentId);
        List<Mark> list=markService.getMarksByStudentId(studentId,pageNumber);
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.student");
    }

    private boolean isEnrolled(Mark mark,long studentId) {
        List<Mark> marksByStudentId = markService.getMarksByStudentId(studentId,ALL_MARKS);
        List<Mark> list = marksByStudentId.stream().filter(m->m.getCourse().getName().equals(mark.getCourse().getName())).collect(Collectors.toList());

        return list.size() != 0;
    }
}
