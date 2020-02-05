package com.github.ralmnsk.srvlt.service.auth;

import com.github.ralmnsk.srvlt.model.Person;
import com.github.ralmnsk.srvlt.service.PersonService;
import com.github.ralmnsk.srvlt.service.PersonServiceInterface;

import javax.servlet.http.HttpServletRequest;

public class LoginLogic {

    public static boolean checkLogin(String enterLogin, String enterPass, HttpServletRequest request) {
        PersonServiceInterface service= PersonService.getInstance();
        Person person=(Person)service.getByLogin(enterLogin);
        if(person.getLogin()!=null){
            if(person.getPassword().equals(enterPass)){
                request.getSession().setAttribute("userId",person.getId());
                request.getSession().setAttribute("userRole",person.getRole().toString());
                return true;
            }
        }
        return false;
    }
}
