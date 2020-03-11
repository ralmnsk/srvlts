package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.service.*;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Scale;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.facultative.service.constants.Constants.*;

public class DoAddMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();
    private IPersonService<Person> personService = PersonServiceImpl.getInstance();
    private Scale scaleFinder = new Scale();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_MARK);
        Course course = (Course)request.getSession().getAttribute(COURSE);

        if(request.getSession().getAttribute(USER_ID) != null){
            long studentId = (long)request.getSession().getAttribute(USER_ID);

            Person student = personService.get(studentId);
            Mark mark = new Mark();
            if (student != null){
                mark.setStudent(student);
                mark.setCourse(course);

                int scale=scaleFinder.getScale(request);

                if(!isEnrolled(mark,studentId, scale) && student.getRole() == UserType.STUDENT){
                    markService.save(mark);
                    request.setAttribute(SEND_REDIRECT,true);

                    return CONTROLLER_COMMAND_VIEW_MARKS;
                } else {
                    request.setAttribute(MARK_EXISTS,MessageManager.getProperty("message.mark.exists"));

                    return ConfigurationManager.getProperty("path.page.error");
                }
            }
        }

        request.setAttribute(NULL_PAGE,MessageManager.getProperty("message.enroll.error"));
        return ConfigurationManager.getProperty("path.page.error");
    }

    private boolean isEnrolled(Mark mark,long studentId, int scale) {
        List<Mark> marksByStudentId = markService.getMarksByStudentId(studentId,ALL_MARKS, scale);
        List<Mark> list = marksByStudentId.stream().filter(m->m.getCourse().getName().equals(mark.getCourse().getName())).collect(Collectors.toList());

        return list.size() != 0;
    }
}
