package com.facultative.service.auth;

import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.model.Person;
import com.facultative.web.password.generator.PasswordGenerator;
import static com.facultative.service.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;

public class LoginLogic {

    private IPersonService service = PersonServiceImpl.getInstance();

    public boolean checkLogin(String enterLogin, String enterPass, HttpServletRequest request) {
        Person person=(Person)service.getByLogin(enterLogin);
        if((person.getLogin() != null) && (person.getPassword() != null)
                && (PasswordGenerator.verifyPassword(enterPass, person.getPassword()))){
                request.getSession().setAttribute(USER_ID,person.getId());
                request.getSession().setAttribute(PERSON,person);
                request.getSession().setAttribute(USER_ROLE,person.getRole().toString());
                return true;
        }
        return false;
    }
}
