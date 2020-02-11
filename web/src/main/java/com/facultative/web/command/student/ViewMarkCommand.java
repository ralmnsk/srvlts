package com.facultative.web.command.student;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class ViewMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_MARK);
        long studentId = (long)request.getSession().getAttribute(USER_ID);

        int pageNumber = Pagination.getPageNumberStudentCourses(request,studentId);
        List<Mark> list = markService.getMarksByStudentId(studentId,pageNumber);
        request.setAttribute(LIST_JSP,list);

        return ConfigurationManager.getProperty("path.page.student");
    }
}
