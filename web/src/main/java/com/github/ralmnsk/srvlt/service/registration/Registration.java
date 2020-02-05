package com.github.ralmnsk.srvlt.service.registration;

import com.github.ralmnsk.srvlt.model.Person;
import com.github.ralmnsk.srvlt.service.PersonService;
import com.github.ralmnsk.srvlt.service.PersonServiceInterface;

public class Registration implements RegistrationInterface{
        private PersonServiceInterface<Person> service=PersonService.getInstance();
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
