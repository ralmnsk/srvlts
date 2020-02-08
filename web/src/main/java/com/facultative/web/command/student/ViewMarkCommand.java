package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.model.Student;
import com.facultative.service.*;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.IPagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class ViewMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_MARK);

        long studentId=(long)request.getSession().getAttribute(USER_ID);
        IMarkService markService=MarkServiceImpl.getInstance();
        int pageNumber= IPagination.getPageNumberStudentCourses(request,studentId);
        List<Mark> list=markService.getMarksByStudentId(studentId,pageNumber);
        request.setAttribute(LIST_JSP,list);

        String page = ConfigurationManager.getProperty("path.page.student");
        return page;
    }
}
