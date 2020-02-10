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
        return  personByLogin.getLogin() != null ;
    }

    @Override
    public boolean register(Person person) {
        service.save(person);
        return isRegistered(person.getLogin());
    }
}
