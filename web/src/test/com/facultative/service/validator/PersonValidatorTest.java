package com.facultative.service.validator;

import com.facultative.model.Person;
import com.facultative.model.UserType;
import com.facultative.web.password.generator.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorTest {

    private PersonValidator validator=new PersonValidator();
    private Person person;
    private Person incorrectPersonName;
    private Person incorrectLoginPassword;
    private String password;
    private String passwordHash;
    private String incorrectPassHash;

    @BeforeEach
    private void set(){
        person = new Person("Вальдемар","Троецкий");
        person.setId(1L);
        person.setLogin("Valdemar");
        password = "123";
        passwordHash = PasswordGenerator.createPassword("123");
        person.setPassword(passwordHash);
        person.setRole(UserType.STUDENT);

        incorrectPersonName = new Person("В","Т");
        incorrectPersonName.setId(1L);
        incorrectPersonName.setLogin("Valdemar");
        incorrectPersonName.setPassword(passwordHash);
        incorrectPersonName.setRole(UserType.STUDENT);

        incorrectLoginPassword = new Person("Виктор","Томилин");
        incorrectLoginPassword.setId(1L);
        incorrectLoginPassword.setLogin("Va");
        incorrectPassHash = PasswordGenerator.createPassword("12");
        incorrectLoginPassword.setPassword(incorrectPassHash);
        incorrectLoginPassword.setRole(UserType.STUDENT);
    }

    @Test
    void isValidCorrect() {
        validator.isValid(person,password);
        assertTrue(validator.isValid(person,password));
    }

    @Test
    void isValidIncorrectName() {
        assertFalse(validator.isValid(incorrectPersonName,incorrectPassHash));
    }

    @Test
    void isValidIncorrectLoginPass() {
        assertFalse(validator.isValid(incorrectPersonName,incorrectPassHash));
    }

    @Test
    void isValidLoginNull(){
        assertFalse(validator.validateLogin(null));

    }
}