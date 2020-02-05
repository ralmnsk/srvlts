package com.github.ralmnsk.srvlt.dao;

import com.github.ralmnsk.srvlt.model.Person;
import com.github.ralmnsk.srvlt.model.UserType;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DaoPersonTest {

    @Test
    void getInstance() {
        assertNotNull(DaoPerson.getInstance());
    }

    @Test
    void save() throws SQLException {
        Person person =new Person("Ivanov","Ivan");
        person.setLogin("login");
        person.setPassword("password");
        person.setRole(UserType.TUITOR);
        DaoInterface<Person> dao= DaoPerson.getInstance();
        Person savePerson = dao.save(person);
        assertTrue(0 != savePerson.getId());
    }

    @Test
    void get() throws SQLException{
        Person person =new Person("Ivanov2","Ivan2");
//        person.setId(100L);
        person.setLogin("login2");
        person.setPassword("password");
        person.setRole(UserType.TUITOR);
        DaoPersonInterface<Person> dao= DaoPerson.getInstance();
//        Person savePerson = dao.save(person);
//        assertTrue(0 != person.getId());

        Person testPerson=dao.get(3L);
        assertTrue(person.getName().equals(testPerson.getName()));

        Person testPerson2=dao.getByLogin("login2");
        assertTrue(person.getName().equals(testPerson2.getName()));

        testPerson.setName("Ivan3");
        testPerson.setLogin("login3");
        dao.update(testPerson);
        assertTrue(person.getName().equals(testPerson2.getName()));

    }

    @Test
    void update() {
    }

    @Test
    void delete() throws SQLException {
        DaoPersonInterface<Person> dao= DaoPerson.getInstance();
        dao.delete(3L);
    }
}