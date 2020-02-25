package com.facultative.web.command;

import com.facultative.model.Person;
import com.facultative.model.UserType;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;

public class LanguageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        if(request.getSession().getAttribute(OLD_COMMAND) !=null){
            String oldCommand = (String)request.getSession().getAttribute(OLD_COMMAND);
            return "/controller?command="+oldCommand;
        }
        if (request.getSession().getAttribute(PERSON) !=null){
            Person person = (Person)request.getSession().getAttribute(PERSON);
            if(person.getRole() == UserType.TUTOR){
                return "/controller?command=tutor";
            }
            if(person.getRole() == UserType.STUDENT){
                return "/controller?command=student";
            }
        }
        return "/controller";
    }
}
