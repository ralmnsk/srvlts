package com.facultative.service.registration;

import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import com.facultative.model.Person;

public class Registration implements IRegistration{
        private IPersonService<Person> service= PersonServiceImpl.getInstance();
    @Override
    public boolean isRegistered(String login) {
        Person personByLogin = service.getByLogin(login);
        if(personByLogin.getLogin()==null){
            return false;
        }
        return true;
    }

    @Override
    public void register(Person person) {
        service.save(person);
    }
}
