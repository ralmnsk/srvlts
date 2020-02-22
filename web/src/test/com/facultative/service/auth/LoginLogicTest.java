package com.facultative.service.auth;

import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class LoginLogicTest {

    private LoginLogic loginLogic = new LoginLogic();
    private Person person;
    private HttpServletRequest request;
    private HttpSession session;
    private IPersonService<Person> service;

    @BeforeEach
    void setLoginLogic(){

        request = mock(HttpServletRequest.class);
        session=mock(HttpSession.class);
        service = mock(PersonServiceImpl.class);

        person=new Person("Вальдемар","Троецкий");
        person.setId(1L);
        person.setLogin("Valdemar");
        person.setPassword("123");
        person.setRole(UserType.STUDENT);
    }

    @Test
    void checkLogin() throws NoSuchFieldException {

        FieldSetter.setField(loginLogic,loginLogic.getClass().getDeclaredField("service"),service);
        when(service.getByLogin(any())).thenReturn(person);
        when(request.getSession()).thenReturn(session);
        boolean check=loginLogic.checkLogin("Valdemar","123",request);
        assertTrue(check);
    }

    @Test
    void checkLoginIncorrect() throws NoSuchFieldException {

        FieldSetter.setField(loginLogic,loginLogic.getClass().getDeclaredField("service"),service);
        when(service.getByLogin(any())).thenReturn(person);
        when(request.getSession()).thenReturn(session);
        boolean check=loginLogic.checkLogin("Valdemar","123j",request);
        assertFalse(check);

    }

    @Test
    void checkLoginDb(){
        Person person = new Person();
        person.setRole(UserType.STUDENT);
        person.setPassword("123");
        person.setLogin("TestLogin");
        person.setName("TestName");
        person.setSurname("TestSurname");

        IPersonService<Person> personService = PersonServiceImpl.getInstance();
        person = personService.save(person);

        when(request.getSession()).thenReturn(session);

        LoginLogic loginLogic = new LoginLogic();
        boolean check = loginLogic.checkLogin("TestLogin", "123", request);
        assertTrue(check);

        personService.delete(person.getId());
    }

    @Test
    void checkLoginDbFalseLogin(){
        Person person = new Person();
        person.setRole(UserType.STUDENT);
        person.setPassword("123");
        person.setLogin("TestLogin");
        person.setName("TestName");
        person.setSurname("TestSurname");

        IPersonService<Person> personService = PersonServiceImpl.getInstance();
        person = personService.save(person);

        when(request.getSession()).thenReturn(session);

        LoginLogic loginLogic = new LoginLogic();
        boolean check = loginLogic.checkLogin("TestLogin1", "123", request);
        assertFalse(check);

        personService.delete(person.getId());
    }

    @Test
    void checkLoginDbFalsePass(){
        Person person = new Person();
        person.setRole(UserType.STUDENT);
        person.setPassword("123");
        person.setLogin("TestLogin");
        person.setName("TestName");
        person.setSurname("TestSurname");

        IPersonService<Person> personService = PersonServiceImpl.getInstance();
        person = personService.save(person);

        when(request.getSession()).thenReturn(session);

        LoginLogic loginLogic = new LoginLogic();
        boolean check = loginLogic.checkLogin("TestLogin", "1231", request);
        assertFalse(check);

        personService.delete(person.getId());
    }
}