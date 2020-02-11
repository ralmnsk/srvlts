package com.facultative.service.auth;

import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.model.Person;
import static com.facultative.service.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;

public class LoginLogic {

    private IPersonService service = PersonServiceImpl.getInstance();

    public boolean checkLogin(String enterLogin, String enterPass, HttpServletRequest request) {
        Person person=(Person)service.getByLogin(enterLogin);
        if(person.getLogin()!= null){
            if(person.getPassword().equals(enterPass)){
                request.getSession().setAttribute(USER_ID,person.getId());
                request.getSession().setAttribute(PERSON,person);
                request.getSession().setAttribute(USER_ROLE,person.getRole().toString());
                return true;
            }
        }
        return false;
    }
}
