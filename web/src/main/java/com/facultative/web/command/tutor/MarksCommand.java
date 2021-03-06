package com.facultative.web.command.tutor;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;
import com.facultative.web.command.pagination.Scale;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class MarksCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();
    private Pagination pagination = new Pagination();
    private Scale scaleFinder = new Scale();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,MARKS_VIEW);

        if(request.getSession().getAttribute(USER_ID) != null){
            long userId = (long)request.getSession().getAttribute(USER_ID);

            int pageNumber = pagination.getPageNumber(request, userId,PAGE_MARK_TUTOR_NUMBER);

            int scale = scaleFinder.getScale(request);
            List<Mark> list = markService.getMarksByTutorId(userId,pageNumber,scale);
            request.setAttribute(LIST_JSP,list);

            return ConfigurationManager.getProperty("path.page.tutor");
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.marks"));
        return ConfigurationManager.getProperty("path.page.error");
    }


}
