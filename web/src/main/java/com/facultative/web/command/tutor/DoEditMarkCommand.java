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

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,MARKS_VIEW);
        int markInt = Integer.parseInt(request.getParameter(MARK));//Mark.getMark() == markInt
        String review = request.getParameter(REVIEW);

        Mark mark = (Mark)request.getSession().getAttribute(MARK);//object of Mark.class
        mark.setMark(markInt);
        mark.setReview(review);
        markService.update(mark);

        return "/controller?command=marks";
    }
}
