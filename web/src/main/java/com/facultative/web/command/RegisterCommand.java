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

    private Registration registration=new Registration();
    private PersonValidator validator=new PersonValidator();

    @Override
    public String execute(HttpServletRequest request) {
        String login= request.getParameter(LOGIN);
        String password= request.getParameter(PASSWORD);
        String surname= request.getParameter(SURNAME);
        String name= request.getParameter(NAME);
        UserType userType=UserType.valueOf(request.getParameter(SELECT_TYPE).toUpperCase());

        String page = ConfigurationManager.getProperty("path.page.registration");
        if(!registration.isRegistered(login)){
            Person person=new Person();
            person.setLogin(login);
            person.setPassword(password);
            person.setSurname(surname);
            person.setName(name);
            person.setRole(userType);

            if(validator.isValid(person)){
                registration.register(person);
                return ConfigurationManager.getProperty("path.page.regsuccsses");
            } else{                                 //find where user mistake during registration
                if (!validator.validateLogin(person.getLogin())) request.setAttribute(LOGIN_ERROR,MessageManager.getProperty("message.login.error"));
                if (!validator.validatePassword(person.getPassword())) request.setAttribute(PASSWORD_ERROR,MessageManager.getProperty("message.password.error"));
                if (!validator.validateSurname(person.getSurname())) request.setAttribute(SURNAME_ERROR,MessageManager.getProperty("message.surname.error"));
                if (!validator.validateSurname(person.getName())) request.setAttribute(NAME_ERROR,MessageManager.getProperty("message.name.error"));
                return page;
            }
        }

        request.setAttribute(ERROR_REG_MESSAGE, MessageManager.getProperty("message.is.registered"));
        return page;
    }
}
