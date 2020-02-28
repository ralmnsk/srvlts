package com.facultative.web.command.student;

import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.service.IMarkService;
import com.facultative.service.IPersonService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;
import com.facultative.web.command.pagination.Scale;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class ViewMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();
    private IPersonService<Person> personService = PersonServiceImpl.getInstance();
    private Pagination pagination = new Pagination();
    private Scale scaleFinder = new Scale();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_MARK);

        if (request.getSession().getAttribute(USER_ID) != null){
            long studentId = (long)request.getSession().getAttribute(USER_ID);
            Person person = personService.get(studentId);

            if ((person != null) && (person.getRole() != null) && (person.getRole() == UserType.STUDENT)) {
                int pageNumber = pagination.getPageNumber(request, studentId, PAGE_MARK_STUDENT_NUMBER);
                int scale = scaleFinder.getScale(request);
                List<Mark> list = markService.getMarksByStudentId(studentId, pageNumber, scale);
                request.setAttribute(LIST_JSP, list);

                return ConfigurationManager.getProperty("path.page.student");
            }
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.marks"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
