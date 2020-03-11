package com.facultative.web.command.student;

import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;

public class DoDelMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if(request.getSession().getAttribute(MARK_ID) != null){
            long markId = (Long)request.getSession().getAttribute(MARK_ID);
            Mark mark = markService.get(markId);
            if (mark != null ){
                Person student = mark.getStudent();
                if (student !=null){
                    long studentIdFromMark = student.getId();
                    if (request.getSession().getAttribute(USER_ID) != null){
                        long userId = (long)request.getSession().getAttribute(USER_ID);
                        if (userId == studentIdFromMark){
                            markService.delete(markId);
                            request.getSession().removeAttribute(MARK_ID);
                            request.setAttribute(SEND_REDIRECT,true);

                            return CONTROLLER_COMMAND_VIEW_MARKS;
                        }
                    }
                }
            }
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.enroll.error"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
