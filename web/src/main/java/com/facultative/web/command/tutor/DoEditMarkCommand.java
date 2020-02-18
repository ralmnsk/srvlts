package com.facultative.web.command.tutor;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DoEditMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();
    private ICourseService<Course> courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,MARKS_VIEW);
        int markInt = Integer.parseInt(request.getParameter(MARK));//Mark.getMark() == markInt
        String review = request.getParameter(REVIEW);

        if (request.getSession().getAttribute(MARK) != null){
            Mark mark = (Mark)request.getSession().getAttribute(MARK);//object of Mark.class
            mark.setMark(markInt);
            mark.setReview(review);
            long tutorIdFromMark = mark.getCourse().getTutor().getId();

            if (request.getSession().getAttribute(USER_ID) != null){
                long userId = (long) request.getSession().getAttribute(USER_ID);
                if (tutorIdFromMark == userId){
                    markService.update(mark);
                    request.getSession().removeAttribute(MARK);
                    return "/controller?command=marks";
                }
            }

        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.edit.mark"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
