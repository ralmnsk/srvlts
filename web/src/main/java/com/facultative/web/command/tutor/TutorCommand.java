package com.facultative.web.command.tutor;

import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.NULL_PAGE;
import static com.facultative.service.constants.Constants.USER_ID;

public class TutorCommand implements ActionCommand {

    IPersonService<Person> personService = PersonServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if(request.getSession().getAttribute(USER_ID) !=null){
            long userId = (long)request.getSession().getAttribute(USER_ID);
            Person person = personService.get(userId);
            if ((person != null) && (person.getRole() != null) && (person.getRole() == UserType.TUTOR)){
                return ConfigurationManager.getProperty("path.page.tutor");
            }
        }
        request.setAttribute(NULL_PAGE, MessageManager.getProperty("message.access.error"));
        return ConfigurationManager.getProperty("path.page.error");
    }
}
