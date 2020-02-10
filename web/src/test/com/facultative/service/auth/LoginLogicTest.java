package com.facultative.service.auth;

import com.facultative.dao.DaoPersonImpl;
import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.service.IPersonService;
import com.facultative.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class LoginLogicTest {

    private LoginLogic loginLogic = new LoginLogic();

    @Test
    void checkLogin() throws NoSuchFieldException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        IPersonService<Person> service = mock(PersonServiceImpl.class);
        FieldSetter.setField(loginLogic,loginLogic.getClass().getDeclaredField("service"),service);
        Person person=new Person("Вальдемар","Троецкий");
        person.setId(1L);
        person.setLogin("Valdemar");
        person.setPassword("123");
        person.setRole(UserType.STUDENT);
        when(service.getByLogin(any())).thenReturn(person);
        when(request.getSession()).thenReturn(session);
        boolean check=loginLogic.checkLogin("Valdemar","123",request);
        assertTrue(check);
    }
}