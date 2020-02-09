package com.facultative.service;

import com.facultative.dao.DaoPersonImpl;
import com.facultative.dao.IDaoPerson;
import com.facultative.model.Person;


/**
 * The type Person service.
 */
public class PersonServiceImpl implements IPersonService<Person> {

    private IDaoPerson<Person> daoPerson= DaoPersonImpl.getInstance();

    private static volatile IPersonService<Person> instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static IPersonService<Person> getInstance() {
        if (instance == null) {
            synchronized (PersonServiceImpl.class) {
                if (instance == null) {
                    instance = new PersonServiceImpl();
                }
            }
        }
        return instance;
    }

    private PersonServiceImpl() {
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
