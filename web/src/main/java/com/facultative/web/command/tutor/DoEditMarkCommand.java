package com.facultative.web.command.tutor;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DoEditMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService;

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,MARKS_VIEW);
        int markInt = Integer.parseInt(request.getParameter(MARK));
        String review = request.getParameter(REVIEW);
        markService = MarkServiceImpl.getInstance();
        Mark mark = (Mark)request.getSession().getAttribute(MARK);
        mark.setMark(markInt);
        mark.setReview(review);
        markService.update(mark);

        long userId=(long)request.getSession().getAttribute(USER_ID);
        List<Mark> list = markService.getMarksByTutorId(userId, Pagination.getPageNumberTutorMarks(request,userId));
        request.setAttribute(LIST_JSP,list);


        return ConfigurationManager.getProperty("path.page.tutor");
    }
}
