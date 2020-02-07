package com.facultative.web.command;

import com.facultative.model.UserType;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import com.facultative.service.registration.Registration;
import com.facultative.service.validator.PersonValidator;
import com.facultative.service.validator.IValidator;
import com.facultative.model.Person;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String login=(String)request.getParameter("login");
        String password=(String)request.getParameter("password");
        String surname=(String)request.getParameter("surname");
        String name=(String)request.getParameter("name");
        UserType userType=UserType.valueOf((String)request.getParameter("selectType").toUpperCase());
        Registration registration=new Registration();
        String page=null;
        page = ConfigurationManager.getProperty("path.page.registration");
        if(!registration.isRegistered(login)){
            Person person=new Person();
            person.setLogin(login);
            person.setPassword(password);
            person.setSurname(surname);
            person.setName(name);
            person.setRole(userType);
            IValidator validator=new PersonValidator();
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
