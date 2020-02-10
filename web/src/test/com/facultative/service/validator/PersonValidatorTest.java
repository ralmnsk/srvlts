package com.facultative.service.validator;

import com.facultative.model.Person;
import com.facultative.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorTest {

    private PersonValidator validator=new PersonValidator();
    private Person person;
    private Person incorrectPersonName;
    private Person incorrectLoginPassword;

    @BeforeEach
    private void set(){
        person = new Person("Вальдемар","Троецкий");
        person.setId(1L);
        person.setLogin("Valdemar");
        person.setPassword("123");
        person.setRole(UserType.STUDENT);

        incorrectPersonName = new Person("В","Т");
        incorrectPersonName.setId(1L);
        incorrectPersonName.setLogin("Valdemar");
        incorrectPersonName.setPassword("123");
        incorrectPersonName.setRole(UserType.STUDENT);

        incorrectLoginPassword = new Person("Витя","Томилин");
        incorrectLoginPassword.setId(1L);
        incorrectLoginPassword.setLogin("Va");
        incorrectLoginPassword.setPassword("12");
        incorrectLoginPassword.setRole(UserType.STUDENT);
    }

    @Test
    void isValidCorrect() {
        validator.isValid(person);
        assertTrue(validator.isValid(person));
    }

    @Test
    void isValidIncorrectName() {
        assertFalse(validator.isValid(incorrectPersonName));
    }

    @Test
    void isValidIncorrectLoginPass() {
        assertFalse(validator.isValid(incorrectPersonName));
    }
}