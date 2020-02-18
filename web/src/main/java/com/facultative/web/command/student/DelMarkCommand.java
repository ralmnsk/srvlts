package com.facultative.web.command.student;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class DelMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,DEL_MARK);

        if(request.getParameter(MARK_ID) != null){
            long markId = Long.parseLong(request.getParameter(MARK_ID));
            request.getSession().setAttribute(MARK_ID,markId);

            Mark mark = markService.get(markId);
            if (mark != null){
                Course course = mark.getCourse();
                request.getSession().setAttribute(COURSE,course);

                return ConfigurationManager.getProperty("path.page.student");
            }
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.del"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
