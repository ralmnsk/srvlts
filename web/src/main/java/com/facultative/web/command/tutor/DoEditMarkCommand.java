package com.facultative.web.command.tutor;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.IPagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DoEditMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,MARKS_VIEW);
        int markInt=Integer.parseInt(request.getParameter(MARK));
        String review=request.getParameter(REVIEW);
        IMarkService<Mark> markService= MarkServiceImpl.getInstance();
        Mark mark=(Mark)request.getSession().getAttribute(MARK);
        mark.setMark(markInt);
        mark.setReview(review);
        markService.update(mark);

        long userId=(long)request.getSession().getAttribute(USER_ID);
        List<Mark> list=markService.getMarksByTutorId(userId, IPagination.getPageNumberTutorMarks(request,userId));
        request.setAttribute(LIST_JSP,list);

        String page = ConfigurationManager.getProperty("path.page.tutor");
        return page;
    }
}
