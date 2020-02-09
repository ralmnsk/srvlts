package com.facultative.web.command.student;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.IPagination;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DoDelMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_MARK);
        long markId=(Long)request.getSession().getAttribute(MARK_ID);
        IMarkService<Mark> markService= MarkServiceImpl.getInstance();
        markService.delete(markId);

        long studentId=(long)request.getSession().getAttribute(USER_ID);
        int pageNumber= IPagination.getPageNumberStudentCourses(request,studentId);
        List<Mark> list=markService.getMarksByStudentId(studentId,pageNumber);
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.student");
    }
}
