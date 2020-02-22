package com.facultative.service.registration;

import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.model.Person;

/**
 * The type Registration.
 */
public class Registration implements IRegistration{

    private IPersonService<Person> service= PersonServiceImpl.getInstance();

    @Override
    public boolean isRegistered(String login) {
        Person personByLogin = service.getByLogin(login);
        if ((personByLogin != null) &&(personByLogin.getLogin() != null) && (personByLogin.getLogin().equals(login))){
            return true;
        }
        return  false ;
    }

    @Override
    public boolean register(Person person) {
        service.save(person);
        return isRegistered(person.getLogin());
    }
}
