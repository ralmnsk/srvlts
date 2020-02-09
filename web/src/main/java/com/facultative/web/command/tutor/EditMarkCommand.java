package com.facultative.web.command.tutor;

import com.facultative.model.Mark;
import com.facultative.model.Person;
import com.facultative.service.IMarkService;
import com.facultative.service.IPersonService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class EditMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PROCESS_FLAG, EDIT_MARK);
        IMarkService<Mark> markService= MarkServiceImpl.getInstance();
        long markId=Long.parseLong(request.getParameter(MARK_ID));
        Mark mark=markService.get(markId);
        if(mark !=null){
            IPersonService<Person> personService= PersonServiceImpl.getInstance();
            Person person=personService.get(mark.getStudent().getId());
            if(person != null){
                mark.getStudent().setName(person.getName());
                mark.getStudent().setSurname(person.getSurname());
                request.getSession().setAttribute(MARK,mark);
            }
        }

        String page = ConfigurationManager.getProperty("path.page.tutor");
        return page;
    }
}
