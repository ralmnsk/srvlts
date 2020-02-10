package com.facultative.service.auth;

import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.model.Person;
import static com.facultative.service.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Login logic.
 */
public class LoginLogic {

    /**
     * Check login boolean.
     *
     * @param enterLogin the enter login
     * @param enterPass  the enter pass
     * @param request    the request
     * @return the boolean
     */
    public boolean checkLogin(String enterLogin, String enterPass, HttpServletRequest request) {
        IPersonService service= PersonServiceImpl.getInstance();
        Person person=(Person)service.getByLogin(enterLogin);
        if(person.getLogin()!=null){
            if(person.getPassword().equals(enterPass)){
                request.getSession().setAttribute(USER_ID,person.getId());
                request.getSession().setAttribute(USER_ROLE,person.getRole().toString());
                return true;
            }
        }
        return false;
    }
}
