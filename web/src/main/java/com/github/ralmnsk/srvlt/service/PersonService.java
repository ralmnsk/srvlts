package com.github.ralmnsk.srvlt.service;

import com.github.ralmnsk.srvlt.dao.DaoPerson;
import com.github.ralmnsk.srvlt.dao.DaoPersonInterface;
import com.github.ralmnsk.srvlt.model.Person;

public class PersonService implements PersonServiceInterface<Person> {
    private DaoPersonInterface<Person> daoPerson= DaoPerson.getInstance();

    private static volatile PersonServiceInterface<Person> instance;

    public static PersonServiceInterface<Person> getInstance() {
        PersonServiceInterface<Person> localInstance = instance;
        if (localInstance == null) {
            synchronized (PersonService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PersonService();
                }
            }
        }
        return localInstance;
    }
    @Override
    public Person getByLogin(String login) {
        return daoPerson.getByLogin(login);
    }

    @Override
    public Person save(Person person){
        return daoPerson.save(person);
    }

    @Override
    public Person get(long id){
        return daoPerson.get(id);
    }

    @Override
    public Person update(Person person){
        return daoPerson.update(person);
    }

    @Override
    public void delete(long id){
        daoPerson.delete(id);
    }
}
