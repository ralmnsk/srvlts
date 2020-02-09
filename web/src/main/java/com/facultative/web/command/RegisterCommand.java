package com.facultative.web.command;

import com.facultative.model.UserType;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.service.registration.Registration;
import com.facultative.service.validator.PersonValidator;
import com.facultative.model.Person;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.*;

public class RegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String login= request.getParameter(LOGIN);
        String password= request.getParameter(PASSWORD);
        String surname= request.getParameter(SURNAME);
        String name= request.getParameter(NAME);
        UserType userType=UserType.valueOf(request.getParameter(SELECT_TYPE).toUpperCase());
        Registration registration=new Registration();
        String page;
        page = ConfigurationManager.getProperty("path.page.registration");
        if(!registration.isRegistered(login)){
            Person person=new Person();
            person.setLogin(login);
            person.setPassword(password);
            person.setSurname(surname);
            person.setName(name);
            person.setRole(userType);
            PersonValidator validator=new PersonValidator();
            if(validator.isValid(person)){
                registration.register(person);
                page = ConfigurationManager.getProperty("path.page.regsuccsses");
                return page;
            }
        }
        request.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.registration.error"));
        return page;
    }
}
