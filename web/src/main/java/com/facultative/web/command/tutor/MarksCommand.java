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

public class MarksCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,MARKS_VIEW);
        IMarkService<Mark> markService= MarkServiceImpl.getInstance();

        long userId=(long)request.getSession().getAttribute(USER_ID);
        int pageNumber= IPagination.getPageNumberTutorMarks(request,userId);
        List<Mark> list=markService.getMarksByTutorId(userId,pageNumber);
        request.setAttribute(LIST_JSP,list);

        String page = ConfigurationManager.getProperty("path.page.tutor");
        return page;
    }


}
