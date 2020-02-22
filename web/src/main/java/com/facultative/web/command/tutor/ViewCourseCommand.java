package com.facultative.web.command.tutor;

import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.model.Course;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.pagination.Pagination;
import com.facultative.web.command.pagination.Scale;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class ViewCourseCommand implements ActionCommand {

    private ICourseService<Course> service = CourseServiceImpl.getInstance();
    private Pagination pagination = new Pagination();
    private Scale scaleFinder = new Scale();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG,VIEW_COURSE);

        int scale = scaleFinder.getScale(request);
        if (request.getSession().getAttribute(USER_ID) != null){
            long userId = (long)request.getSession().getAttribute(USER_ID);
            int pageNumber= pagination.getPageNumber(request,userId,PAGE_COURSE_TUTOR_NUMBER);
            List<Course> list=service.getCoursesByTutorId(userId,pageNumber,scale);//number of the page to go
            request.setAttribute(LIST_JSP,list);

            return ConfigurationManager.getProperty("path.page.tutor");
        }

        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.error.courses"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
